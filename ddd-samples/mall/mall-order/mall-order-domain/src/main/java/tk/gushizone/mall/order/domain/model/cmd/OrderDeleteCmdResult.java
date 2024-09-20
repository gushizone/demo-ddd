package tk.gushizone.mall.order.domain.model.cmd;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.entity.OrderEntity;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
@Accessors(chain = true)
public class OrderDeleteCmdResult {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;
}
