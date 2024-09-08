package tk.gushizone.infra.libs.core.exception;

import cn.hutool.core.util.StrUtil;
import tk.gushizone.infra.libs.core.common.BaseEnum;
import tk.gushizone.infra.libs.core.web.Status;

/**
 * 通用的业务异常类
 *
 * @author gushizone
 * @since 2023/6/2
 */
public class BusinessException extends RuntimeException {

    private int code;

    private BaseEnum status;

    public BusinessException(String msg, Object... args) {
        super(StrUtil.format(msg, args));
        this.code = Status.FAIL_OPERATION.code();
        this.status = Status.FAIL_OPERATION;
    }

    public BusinessException(BaseEnum status, Object... args) {
        super(StrUtil.format(status.label(), args));
        this.code = status.code();
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public BaseEnum getStatus() {
        return status;
    }
}