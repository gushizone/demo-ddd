package tk.gushizone.mall.order.domain.model.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;

/**
 * 订单已创建事件
 *
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderCreatedEvent {

    private OrderAggregate orderAggregate;
}
