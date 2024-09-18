package tk.gushizone.mall.order.infrastructure.repository.db.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import tk.gushizone.infra.libs.core.mybatisplus.RevisionModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author gushizone
 * @since 2024-09-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("`order`")
public class Order extends RevisionModel {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货地址id
     */
    private Long shippingId;

    /**
     * 实际付款金额
     */
    private BigDecimal payment;

    /**
     * 订单状态(1002)
     */
    private Integer orderStatus;
}
