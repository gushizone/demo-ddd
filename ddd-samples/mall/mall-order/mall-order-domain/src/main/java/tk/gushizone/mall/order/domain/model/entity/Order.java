package tk.gushizone.mall.order.domain.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import tk.gushizone.infra.libs.base.entity.RevisionEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author gushizone
 * @since 2024-09-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("`order`")
public class Order extends RevisionEntity {

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
