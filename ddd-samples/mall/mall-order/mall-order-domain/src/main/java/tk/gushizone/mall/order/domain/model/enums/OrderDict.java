package tk.gushizone.mall.order.domain.model.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.value.BaseDict;
import tk.gushizone.infra.libs.base.value.BaseEnum;

/**
 * 字典
 *
 * @author gushizone
 * @since 2024/9/14
 */
@AllArgsConstructor
public enum OrderDict implements BaseDict {

    ORDER_TYPE(null, 1001, "订单类型"),
    ORDER_TYPE_1(ORDER_TYPE, 1001_1001, "普通"),
    ORDER_TYPE_2(ORDER_TYPE, 1001_1002, "大客户"),

    ORDER_STATUS(null, 1002, "订单状态"),
    ORDER_STATUS_1(ORDER_STATUS, 1002_1001, "已取消"),
    ORDER_STATUS_2(ORDER_STATUS, 1002_1002, "为支付"),
    ORDER_STATUS_3(ORDER_STATUS, 1002_1003, "已发货"),
    ORDER_STATUS_4(ORDER_STATUS, 1002_1004, "订单完成"),
    ORDER_STATUS_5(ORDER_STATUS, 1002_1005, "已关闭"),


    ;

    private final BaseEnum parent;

    private final int code;

    private final String label;

    @Override
    public BaseEnum parent() {
        return parent;
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
