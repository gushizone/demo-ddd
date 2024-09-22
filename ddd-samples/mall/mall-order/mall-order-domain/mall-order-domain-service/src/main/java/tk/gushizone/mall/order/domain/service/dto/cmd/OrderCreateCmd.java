package tk.gushizone.mall.order.domain.service.dto.cmd;

import lombok.Data;
import tk.gushizone.mall.order.domain.service.dto.cmd.common.OrderItemCmd;

import java.util.List;

/**
 * @author gushizone
 * @since 2024/9/20
 */
@Data
public class OrderCreateCmd {

    private Long userId;

    private Long shippingId;

    private List<OrderItemCmd> orderItems;
}
