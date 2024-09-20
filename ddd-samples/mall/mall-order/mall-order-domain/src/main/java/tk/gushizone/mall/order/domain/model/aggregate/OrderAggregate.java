package tk.gushizone.mall.order.domain.model.aggregate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:20
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderAggregate extends Order {

    /**
     * 订单项
     */
    private List<OrderItem> orderItems;
}
