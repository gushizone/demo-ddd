package tk.gushizone.mall.order.application.dto.rsp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gushizone
 * @since 2022/10/18 15:52
 */
@Data
public class OrderItemRsp {

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
