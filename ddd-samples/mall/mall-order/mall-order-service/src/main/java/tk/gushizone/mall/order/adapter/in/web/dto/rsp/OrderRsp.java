package tk.gushizone.mall.order.adapter.in.web.dto.rsp;

import java.math.BigDecimal;
import java.util.List;

import tk.gushizone.infra.libs.core.rest.VersionRsp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author gushizone
 * @since 2024-09-18
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "Order", description = "订单")
public class OrderRsp extends VersionRsp {

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "收货地址id")
    private Long shippingId;

    @Schema(description = "实际付款金额")
    private BigDecimal payment;

    @Schema(description = "订单状态(1002)")
    private Integer orderStatus;

    @Schema(description = "订单项")
    private List<OrderItemRsp> orderItems;
}
