package tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
@Schema(description = "订单项")
public class OrderItemCmdReq {

    @NotNull
    @Schema(description = "产品")
    private Long productId;

    @Null
    @Min(value = 1)
    @Schema(description = "数量")
    private Integer quantity;
}
