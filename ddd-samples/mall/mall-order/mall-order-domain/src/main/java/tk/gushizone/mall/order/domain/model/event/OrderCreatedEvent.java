package tk.gushizone.mall.order.domain.model.event;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * 订单已创建事件
 *
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
public class OrderCreatedEvent {

    private Order order;

    private List<OrderItem> orderItems;

    public void linking() {
        if (CollectionUtils.isEmpty(orderItems)) {
            return;
        }
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
        }
    }
}
