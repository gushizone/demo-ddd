package tk.gushizone.mall.order.infrastructure.repository.db.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.gushizone.infra.libs.core.mybatisplus.ControllableEntity;

import java.math.BigDecimal;

/**
 * 订单项
 */
@Data
@TableName(value = "order_item")
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends ControllableEntity {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户id
     */
    private Long userId;

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