package tk.gushizone.mall.order.domain.model.aggregate;

import lombok.Data;
import tk.gushizone.mall.order.domain.model.entity.OrderEntity;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 18:20
 */
@Data
public class OrderAggregate extends OrderEntity {

    /**
     * 订单项
     */
    private List<OrderItemEntity> orderItems;
}
