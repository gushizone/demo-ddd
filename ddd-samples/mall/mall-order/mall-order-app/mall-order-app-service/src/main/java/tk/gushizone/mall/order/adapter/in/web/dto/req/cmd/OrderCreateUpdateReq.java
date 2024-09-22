package tk.gushizone.mall.order.adapter.in.web.dto.req.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
@Schema(description = "订单修改命令")
public class OrderCreateUpdateReq {

    @NotNull
    @Schema(description = "地址")
    private Long shippingId;

}
