package tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
@Schema(description = "订单项")
public class OrderItemCmdReq {

    @Schema(description = "产品")
    private Long productId;

    @Schema(description = "数量")
    private Integer quantity;
}
