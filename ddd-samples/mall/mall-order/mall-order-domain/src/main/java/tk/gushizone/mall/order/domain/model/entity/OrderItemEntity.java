package tk.gushizone.mall.order.domain.model.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单项
 */
@Data
public class OrderItemEntity {
    /**
     * id
     */
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long orderId;

    /**
     *
     */
    private Long orderNo;

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
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价,单位是元,保留两位小数
     */
    private BigDecimal totalPrice;

}