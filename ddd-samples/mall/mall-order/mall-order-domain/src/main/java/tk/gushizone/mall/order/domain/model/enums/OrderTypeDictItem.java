package tk.gushizone.mall.order.domain.model.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.BaseEnum;

/**
 * 业务异常
 */
@AllArgsConstructor
public enum OrderTypeDictItem implements BaseEnum {

    ORDER_TYPE_NORMAL(7030_1001_1001L, "订单类型"),
    ORDER_TYPE_FLEET(7030_1001_1002L, "订单类型"),


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
