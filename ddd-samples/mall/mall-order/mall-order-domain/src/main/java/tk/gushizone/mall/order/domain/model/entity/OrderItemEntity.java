package tk.gushizone.mall.order.domain.model.entity;

import java.math.BigDecimal;
import tk.gushizone.infra.libs.base.entity.RevisionEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单项
 * </p>
 *
 * @author gushizone
 * @since 2024-09-17
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderItemEntity extends RevisionEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片地址
     */
    private String productImage;

    /**
     * 生成订单时的商品单价
     */
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;
}
