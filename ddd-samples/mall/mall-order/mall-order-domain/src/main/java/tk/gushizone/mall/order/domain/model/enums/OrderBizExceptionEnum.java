package tk.gushizone.mall.order.domain.model.enums;

import lombok.AllArgsConstructor;
import tk.gushizone.infra.libs.base.BaseEnum;

/**
 * 业务异常
 */
@AllArgsConstructor
public enum OrderBizExceptionEnum implements BaseEnum {

    NOT_FOUND(7030_4006_1001L, "订单不存在");

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
