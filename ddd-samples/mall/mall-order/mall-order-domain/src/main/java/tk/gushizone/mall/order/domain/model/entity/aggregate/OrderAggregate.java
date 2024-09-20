package tk.gushizone.mall.order.domain.model.entity.aggregate;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.entity.BaseAggregate;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @since 2022/10/18 18:20
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderAggregate extends BaseAggregate<Order> {

    /**
     * 订单项
     */
    private List<OrderItem> orderItems;

    public static List<OrderAggregate> of(List<Order> orders,
                                          Map<Long, List<OrderItem>> orderToItemMap) {
        if (CollectionUtils.isEmpty(orders)) {
            return Lists.newArrayList();
        }
        List<OrderAggregate> results = Lists.newArrayListWithExpectedSize(orders.size());
        for (Order order : orders) {

            OrderAggregate result = new OrderAggregate();
            result.setRoot(order);
            result.setOrderItems(orderToItemMap.getOrDefault(order.getId(), Lists.newArrayList()));
            results.add(result);
        }
        return results;
    }

    public void linking() {
        if (CollectionUtils.isEmpty(orderItems)) {
            return;
        }
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(getRoot().getId());
        }
    }
}
