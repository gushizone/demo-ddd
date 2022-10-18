package tk.gushizone.mall.order.application.dto.req.cmd.common;

import lombok.Data;

/**
 * @author gushizone
 * @date 2022/10/18 15:12
 */
@Data
public class OrderItemCmdReq {

    private Long productId;

    private Integer quantity;
}
