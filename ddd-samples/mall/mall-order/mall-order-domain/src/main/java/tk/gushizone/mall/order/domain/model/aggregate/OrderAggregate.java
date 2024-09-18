package tk.gushizone.mall.order.domain.model.aggregate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tk.gushizone.mall.order.domain.model.entity.OrderEntity;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:20
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderAggregate extends OrderEntity {

    /**
     * 订单项
     */
    private List<OrderItemEntity> orderItems;
}
