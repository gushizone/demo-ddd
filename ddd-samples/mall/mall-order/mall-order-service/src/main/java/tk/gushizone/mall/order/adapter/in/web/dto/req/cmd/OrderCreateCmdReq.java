package tk.gushizone.mall.order.adapter.in.web.dto.req.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common.OrderItemCmdReq;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
@Schema(description = "订单新增命令")
public class OrderCreateCmdReq {

    @NotNull
    @Schema(description = "地址")
    private Long shippingId;

    @Schema(description = "订单项")
    private List<OrderItemCmdReq> orderItems;

}
