package tk.gushizone.mall.order.domain.model.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.BaseEnum;

/**
 * 业务异常
 */
@AllArgsConstructor
public enum OrderDict implements BaseEnum {

    ORDER_TYPE(7030_1001, "订单类型"),
    ORDER_STATUS(7030_1002, "订单状态"),


    ;

    private final long code;

    private final String label;

    @Override
    public long code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
