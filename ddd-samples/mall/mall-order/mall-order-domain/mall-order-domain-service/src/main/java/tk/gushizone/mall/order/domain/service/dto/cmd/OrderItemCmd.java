package tk.gushizone.mall.order.domain.service.dto.cmd;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gushizone
 * @since 2022/10/18 18:02
 */
@Data
public class OrderItemCmd {
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

}
