package tk.gushizone.mall.order.adapter.in.web.dto.rsp;

import java.math.BigDecimal;
import tk.gushizone.infra.libs.core.rest.DomainRsp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单项
 * </p>
 *
 * @author gushizone
 * @since 2024-09-18
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "OrderItem", description = "订单项")
public class OrderItemRsp extends DomainRsp {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "订单id")
    private Long orderId;

    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品图片地址")
    private String productImage;

    @Schema(description = "生成订单时的商品单价")
    private BigDecimal currentUnitPrice;

    @Schema(description = "商品数量")
    private Integer quantity;

    @Schema(description = "商品总价")
    private BigDecimal totalPrice;
}
