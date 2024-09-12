package tk.gushizone.mall.order.application.dto.rsp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author gushizone
 * @since 2022/10/18 15:51
 */
@Data
public class OrderRsp {

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

    /**
     * 订单项
     */
    private List<OrderItemRsp> orderItems;

}
