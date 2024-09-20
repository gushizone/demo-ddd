package tk.gushizone.mall.order.domain.model.cmd;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
@Accessors(chain = true)
public class OrderDeleteCmdResult {

    private Order order;

    private List<OrderItem> orderItems;
}
