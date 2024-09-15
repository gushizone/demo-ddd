package tk.gushizone.infra.libs.base.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import tk.gushizone.infra.libs.base.value.BaseEnum;

/**
 * 通用的业务异常类
 * - 所有异常必须定义枚举
 * - 支持国际化 todo
 *
 * @author gushizone
 * @since 2023/6/2
 */
@Getter
public class BizException extends RuntimeException {

    private final long code;

    private final BaseEnum status;

    protected BizException(BaseEnum status, Object... args) {
        super(StrUtil.format(status.label(), args));
        this.code = status.code();
        this.status = status;
    }

    public static BizException of(BaseEnum status, Object... args) {
        return new BizException(status, args);
    }
}