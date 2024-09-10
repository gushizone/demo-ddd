package tk.gushizone.mall.order.domain.model.cmd;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.exception.BusinessException;
import tk.gushizone.mall.order.domain.model.entity.OrderEntity;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * todo 通用抽取
 *
 * @author gushizone
 * @date 2022/10/18 18:01
 */
@Data
public class OrderCreateCmd {

    private Long userId;

    private Long shippingId;

    private List<OrderItemCmd> orderItems;

    public OrderCreateCmdResult exec() {

        OrderCreateCmdResult result = new OrderCreateCmdResult();

        result.setOrder(buildOrder());

        result.setOrderItems(buildOrderItem());

//        result.getOrder().setPayment(); // todo

        return result;
    }

    private List<OrderItemEntity> buildOrderItem() {
        if (CollectionUtils.isEmpty(orderItems)) {
            throw new BusinessException("订单项不能为空");
        }
        for (OrderItemCmd orderItem : orderItems) {
            if (orderItem.getQuantity() > orderItem.getStockQty()) {
                throw new BusinessException("库存不足");
            }
        }
        List<OrderItemEntity> results = Lists.newArrayListWithExpectedSize(orderItems.size());
        for (OrderItemCmd orderItemCmd : orderItems) {
            OrderItemEntity result = new OrderItemEntity();
//            result.setId();
//            result.setUserId();
//            result.setOrderNo();
            result.setProductId(orderItemCmd.getProductId());
            result.setProductName(orderItemCmd.getProductName());
            result.setProductImage(orderItemCmd.getProductImage());
            result.setCurrentUnitPrice(orderItemCmd.getCurrentUnitPrice());
            result.setQuantity(orderItemCmd.getQuantity());
            result.setTotalPrice(result.getCurrentUnitPrice().multiply(new BigDecimal(orderItemCmd.getQuantity()))); // todo
            results.add(result);
        }
        return results;
    }

    private OrderEntity buildOrder() {
        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setId();
//        orderEntity.setOrderNo();
//        orderEntity.setUserId();
        orderEntity.setShippingId(shippingId);
//        orderEntity.setPayment(); // todo
        orderEntity.setOrderStatus(10); // todo
        return orderEntity;
    }
}