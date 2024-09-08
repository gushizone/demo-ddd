package tk.gushizone.mall.order.domain.model.cmd;

import lombok.Data;
import tk.gushizone.mall.order.domain.model.entity.OrderEntity;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 18:01
 */
@Data
public class OrderCreateCmdResult {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;
}
