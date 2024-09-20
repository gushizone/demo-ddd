package tk.gushizone.mall.order.adapter.out.repository.dto;

import lombok.Data;
import tk.gushizone.mall.order.infrastructure.repository.db.po.Order;
import tk.gushizone.mall.order.infrastructure.repository.db.po.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
public class OrderDeleteDmResult {

    private Order order;

    private List<OrderItem> orderItems;
}
