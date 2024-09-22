package tk.gushizone.mall.order.domain.service.handler;

import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderModifyCmd;

/**
 *
 * @author gushizone
 * @since 2022/10/18 18:01
 */
public class OrderModifyCmdHandler {

    public static OrderAggregate handle(OrderModifyCmd cmd) {
        OrderAggregate result = cmd.getOrderAggregate();
        result.getRoot().setShippingId(cmd.getShippingId());
        result.setVersion(cmd.getVersion());
        return result;
    }
}
