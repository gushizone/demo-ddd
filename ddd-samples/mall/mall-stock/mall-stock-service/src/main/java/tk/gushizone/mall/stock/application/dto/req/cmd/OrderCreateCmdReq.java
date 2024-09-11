package tk.gushizone.mall.stock.application.dto.req.cmd;

import lombok.Data;
import tk.gushizone.mall.stock.application.dto.req.cmd.common.OrderItemCmdReq;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
public class OrderCreateCmdReq {

    private Long userId;

    private Long shippingId;

    private List<OrderItemCmdReq> orderItems;

}
