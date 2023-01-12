package tk.gushizone.mall.order.domain.model.value.cmd.common;

import lombok.Data;

/**
 * @author gushizone
 * @date 2022/10/18 18:02
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
     * 商品数量
     */
    private Integer quantity;


}
