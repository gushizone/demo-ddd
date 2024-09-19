package tk.gushizone.mall.order.domain.model.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.enums.BaseDict;
import tk.gushizone.infra.libs.base.enums.BaseEnum;
import tk.gushizone.mall.order.domain.model.contant.OrderDictTypes;

/**
 * 字典
 *
 * @author gushizone
 * @since 2024/9/14
 */
@AllArgsConstructor
public enum OrderDict implements BaseDict {

    ORDER_TYPE(null, OrderDictTypes.ORDER_TYPE, "订单类型"),
    ORDER_TYPE_NORMAL(ORDER_TYPE, 1001_1001, "普通"),
    ORDER_TYPE_FLEET(ORDER_TYPE, 1001_1002, "大客户"),

    ORDER_STATUS(null, OrderDictTypes.ORDER_STATUS, "订单状态"),
    ORDER_STATUS_CANCELED(ORDER_STATUS, 1002_1001, "已取消"),
    ORDER_STATUS_UNPAID(ORDER_STATUS, 1002_1002, "未支付"),
    ORDER_STATUS_SHIPPED(ORDER_STATUS, 1002_1003, "已发货"),
    ORDER_STATUS_DONE(ORDER_STATUS, 1002_1004, "已完成"),
    ORDER_STATUS_CLOSED(ORDER_STATUS, 1002_1005, "已关闭"),


    ;

    private final BaseEnum type;

    private final int code;

    private final String label;

    @Override
    public BaseEnum type() {
        return type;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
