package tk.gushizone.mall.order.domain.model.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.value.BaseEnum;

/**
 * 业务异常
 */
@AllArgsConstructor
public enum OrderErrors implements BaseEnum {

    ORDER_NOT_FOUND(7030_4001, "订单不存在"),
    ORDER_ITEM_EMPTY(7030_4002, "订单项不能为空"),
    INSUFFICIENT_INVENTORY(7030_4003, "库存不足"),

    ;

    private final int code;

    private final String label;

    @Override
    public int code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
