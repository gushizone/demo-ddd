package tk.gushizone.infra.libs.core.web;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.gushizone.infra.libs.core.exception.BusinessException;

import java.io.Serializable;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static RestResponse<Void> ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T data) {
        return RestResponse.<T>builder()
                .code(Status.OK.code())
                .msg(Status.OK.label())
                .data(data)
                .build();
    }

    /**
     * 一般业务不要直接使用这个方法, 直接抛出业务异常即可
     */
    public static <T> RestResponse<T> fail(String msg) {
        return RestResponse.<T>builder()
                .code(Status.FAIL_OPERATION.code())
                .msg(msg)
                .build();
    }

    /**
     * 一般业务不要直接使用这个方法, 直接抛出业务异常即可
     */
    public static <T> RestResponse<T> fail(BusinessException ex) {
        return RestResponse.<T>builder()
                .code(ex.getCode())
                .msg(ex.getMessage())
                .build();
    }
}
