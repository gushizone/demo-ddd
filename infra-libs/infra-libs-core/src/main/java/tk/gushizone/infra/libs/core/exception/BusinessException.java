package tk.gushizone.infra.libs.core.exception;

import cn.hutool.core.util.StrUtil;
import tk.gushizone.infra.libs.core.web.Status;

/**
 * 通用的业务异常类
 */
public class BusinessException extends RuntimeException {

    private int code;

    /**
     * todo 不应该用枚举, 不然不好扩展???
     */
    private Status status;

    public BusinessException(String msg, Object... args) {
        super(StrUtil.format(msg, args));
        this.code = Status.FAIL_OPERATION.code();
        this.status = Status.FAIL_OPERATION;
    }

    public Integer getCode() {
        return code;
    }

    public Status getStatus() {
        return status;
    }
}