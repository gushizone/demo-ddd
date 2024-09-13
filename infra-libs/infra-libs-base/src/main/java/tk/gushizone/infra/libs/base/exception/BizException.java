package tk.gushizone.infra.libs.base.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import tk.gushizone.infra.libs.base.BaseEnum;
import tk.gushizone.infra.libs.base.Status;

/**
 * 通用的业务异常类  todo 业务异常代码国际化
 *
 * @author gushizone
 * @since 2023/6/2
 */
@Getter
public class BizException extends RuntimeException {

    private final long code;

    private final BaseEnum status;

    public BizException(String msg, Object... args) {
        super(StrUtil.format(msg, args));
        this.code = Status.FAIL_OPERATION.code();
        this.status = Status.FAIL_OPERATION;
    }

    public BizException(BaseEnum status, Object... args) {
        super(StrUtil.format(status.label(), args));
        this.code = status.code();
        this.status = status;
    }
}