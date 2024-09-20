package tk.gushizone.mall.order.domain.model.cmd;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;
import tk.gushizone.mall.order.domain.model.enums.OrderDict;
import tk.gushizone.mall.order.domain.model.enums.OrderErrors;

import java.math.BigDecimal;
import java.util.List;

/**
 * todo 通用抽取
 *
 * @author gushizone
 * @since 2022/10/18 18:01
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

        // 订单总价
        List<BigDecimal> totalPrices = ModelUtils.map(result.getOrderItems(), OrderItem::getTotalPrice);
        result.getOrder().setPayment(NumberUtil.add(totalPrices.toArray(new BigDecimal[0])));

        return result;
    }

    private List<OrderItem> buildOrderItem() {
        if (CollectionUtils.isEmpty(orderItems)) {
            throw BizException.of(OrderErrors.ORDER_ITEM_EMPTY);
        }
        List<OrderItem> results = Lists.newArrayListWithExpectedSize(orderItems.size());
        for (OrderItemCmd orderItemCmd : orderItems) {
            OrderItem result = new OrderItem();
            result.setUserId(userId);
//            result.setOrderNo(); // todo
            result.setProductId(orderItemCmd.getProductId());
            result.setProductName(orderItemCmd.getProductName());
            result.setProductImage(orderItemCmd.getProductImage());
            result.setCurrentUnitPrice(orderItemCmd.getCurrentUnitPrice());
            result.setQuantity(orderItemCmd.getQuantity());
            result.setTotalPrice(NumberUtil.mul(result.getCurrentUnitPrice(), result.getQuantity()));
            results.add(result);
        }
        return results;
    }

    private Order buildOrder() {
        Order order = new Order();
//        orderEntity.setOrderNo();
        order.setUserId(userId);
        order.setShippingId(shippingId);
        order.setOrderStatus(OrderDict.ORDER_STATUS_UNPAID.code());
        return order;
    }
}
