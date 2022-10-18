package tk.gushizone.mall.order.domain.model.aggregate;

import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 18:20
 */
public class OrderAggregate {

    /**
     * id
     */
    private Long id;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货地址id todo
     */
    private Long shippingId;

    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    private BigDecimal payment;

    /**
     * 支付类型: 1=在线支付
     */
    private Integer paymentType;

    /**
     * 运费,单位是元
     */
    private Integer postage;

    /**
     * 订单状态: 0=已取消, 10=未付款, 20=已付款, 40=已发货, 50=交易成功, 60=交易关闭
     */
    private Integer status;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

    /**
     * 订单项
     */
    private List<OrderItemEntity> orderItems;
}
