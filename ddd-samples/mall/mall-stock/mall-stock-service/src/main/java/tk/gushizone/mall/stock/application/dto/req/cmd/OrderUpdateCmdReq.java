package tk.gushizone.mall.stock.application.dto.req.cmd;

import lombok.Data;
import tk.gushizone.mall.stock.application.dto.req.cmd.common.OrderItemCmdReq;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 15:12
 */
@Data
public class OrderUpdateCmdReq {

    private Long id;

    private List<OrderItemCmdReq> orderItems;

}
