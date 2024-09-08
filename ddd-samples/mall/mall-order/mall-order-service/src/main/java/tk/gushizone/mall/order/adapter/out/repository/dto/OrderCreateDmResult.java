package tk.gushizone.mall.order.adapter.out.repository.dto;

import lombok.Data;
import tk.gushizone.mall.order.adapter.out.repository.assembler.converter.OrderItemRepositoryConvertor;
import tk.gushizone.mall.order.adapter.out.repository.assembler.converter.OrderRepositoryConvertor;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.infrastructure.repository.db.po.Order;
import tk.gushizone.mall.order.infrastructure.repository.db.po.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 18:01
 */
@Data
public class OrderCreateDmResult {

    private Order order;

    private List<OrderItem> orderItems;

    public static OrderCreateDmResult valueOf(OrderCreateCmdResult orderCreateCmdResult) {
        OrderCreateDmResult result = new OrderCreateDmResult();
        result.setOrder(OrderRepositoryConvertor.INSTANCE.toPo(orderCreateCmdResult.getOrder()));
        result.setOrderItems(OrderItemRepositoryConvertor.INSTANCE.toPoList(orderCreateCmdResult.getOrderItems()));
        return result;
    }
}
