package tk.gushizone.mall.order.domain.service.handler;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.api.enums.OrderDict;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;
import tk.gushizone.mall.order.domain.model.enums.OrderErrors;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.common.OrderItemCmd;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gushizone
 * @since 2024/9/22
 */
public class OrderCreateCmdHandler {


    public static OrderAggregate handle(OrderCreateCmd cmd) {

        OrderAggregate orderAggregate = new OrderAggregate(buildOrder(cmd));
        orderAggregate.setOrderItems(buildOrderItem(orderAggregate.getRoot(), cmd.getOrderItems()));
        return orderAggregate;
    }

    private static List<OrderItem> buildOrderItem(Order order,
                                                  List<OrderItemCmd> orderItems) {
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

    private static Order buildOrder(OrderCreateCmd cmd) {
        Order order = new Order();
//        orderEntity.setOrderNo();
        order.setUserId(cmd.getUserId());
        order.setShippingId(cmd.getShippingId());
        order.setOrderStatus(OrderDict.ORDER_STATUS_UNPAID.code());
        return order;
    }
}
