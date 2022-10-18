package tk.gushizone.mall.order.application.dto.req.cmd;

import lombok.Data;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 15:12
 */
@Data
public class OrderCreateCmdReq {

    private Long userId;

    private Long shippingId;

    private List<OrderItemCmdReq> orderItems;

}
