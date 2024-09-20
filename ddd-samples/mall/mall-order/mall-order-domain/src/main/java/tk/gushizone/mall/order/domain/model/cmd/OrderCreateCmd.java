package tk.gushizone.mall.order.domain.model.cmd;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.enums.OrderDict;
import tk.gushizone.mall.order.domain.model.enums.OrderErrors;
import tk.gushizone.mall.order.domain.model.event.OrderCreatedEvent;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gushizone
 * @since 2024/9/20
 */
@Data
public class OrderCreateCmd {

    private Long userId;

    private Long shippingId;

    private List<OrderItemCmd> orderItems;

    public OrderCreatedEvent exec() {
        OrderCreatedEvent result = new OrderCreatedEvent();

        OrderAggregate orderAggregate = new OrderAggregate();
        orderAggregate.setRoot(buildOrder());
        orderAggregate.setOrderItems(buildOrderItem(orderAggregate.getRoot()));

        result.setOrderAggregate(orderAggregate);
        return result;
    }

    private List<OrderItem> buildOrderItem(Order order) {
        if (CollectionUtils.isEmpty(orderItems)) {
            throw BizException.of(OrderErrors.ORDER_ITEM_EMPTY);
        }
        List<OrderItem> results = Lists.newArrayListWithExpectedSize(orderItems.size());
        for (OrderItemCmd orderItemCmd : orderItems) {
            OrderItem result = new OrderItem();
            result.setUserId(order.getUserId());
//            result.setOrderNo(); // todo
            result.setProductId(orderItemCmd.getProductId());
            result.setProductName(orderItemCmd.getProductName());
            result.setProductImage(orderItemCmd.getProductImage());
            result.setCurrentUnitPrice(orderItemCmd.getCurrentUnitPrice());
            result.setQuantity(orderItemCmd.getQuantity());
            result.setTotalPrice(NumberUtil.mul(result.getCurrentUnitPrice(), result.getQuantity()));
            results.add(result);
        }
        // 订单总价
        List<BigDecimal> totalPrices = ModelUtils.map(results, OrderItem::getTotalPrice);
        order.setPayment(NumberUtil.add(totalPrices.toArray(new BigDecimal[0])));
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
