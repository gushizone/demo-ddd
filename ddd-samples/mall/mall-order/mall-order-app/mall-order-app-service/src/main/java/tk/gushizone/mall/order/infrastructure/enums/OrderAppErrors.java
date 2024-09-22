package tk.gushizone.mall.order.infrastructure.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.enums.BaseEnum;

/**
 * 业务异常
 *
 * @author gushizone
 * @since 2024/9/16
 */
@AllArgsConstructor
public enum OrderAppErrors implements BaseEnum {

    PRODUCT_NOT_FOUND(7030_4501, "产品不存在"),
    INSUFFICIENT_INVENTORY(7030_4502, "库存不足"),

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
