package tk.gushizone.mall.order.adapter.out.remote.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gushizone
 * @date 2022/10/19 10:44
 */
@Data
public class Product {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片地址
     */
    private String image;

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    private BigDecimal currentUnitPrice;
}
