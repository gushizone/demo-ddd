package tk.gushizone.mall.order.adapter.in.web.dto.req.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tk.gushizone.infra.libs.base.validation.constraints.Dict;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.domain.api.contant.OrderDictTypes;
import tk.gushizone.mall.order.domain.api.enums.OrderDict;

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

    @Schema(description = "订单类型")
    @Dict(dict = OrderDict.class, type = OrderDictTypes.ORDER_TYPE)
    private Integer type;

    @Schema(description = "订单项")
    private List<OrderItemCmdReq> orderItems;

}
