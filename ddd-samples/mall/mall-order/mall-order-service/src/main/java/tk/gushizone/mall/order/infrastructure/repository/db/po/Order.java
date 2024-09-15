package tk.gushizone.mall.order.infrastructure.repository.db.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.gushizone.infra.libs.core.mybatisplus.ControllableEntity;

import java.math.BigDecimal;

/**
 * 订单
 */
@Data
@TableName(value = "`order`")
@EqualsAndHashCode(callSuper = true)
public class Order extends ControllableEntity {

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货地址id
     */
    private Long shippingId;

    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    private BigDecimal payment;

    /**
     * 订单状态: 0=已取消, 10=未付款, 20=已付款, 40=已发货, 50=交易成功, 60=交易关闭
     */
    private Integer orderStatus;
}