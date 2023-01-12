package tk.gushizone.mall.order.domain.model.value.cmd;

import lombok.Data;
import tk.gushizone.mall.order.domain.model.value.cmd.common.OrderItemCmd;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 18:01
 */
@Data
public class OrderCreateCmd {

    private Long userId;

    private Long shippingId;

    private List<OrderItemCmd> orderItems;

}
