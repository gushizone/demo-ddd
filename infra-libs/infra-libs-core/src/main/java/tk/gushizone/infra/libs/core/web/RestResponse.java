package tk.gushizone.infra.libs.core.web;


import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author gushizone
 * @since 2023/6/1
 */
@Data
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

    public RestResponse() {
    }

    /**
     * 一般业务不要直接使用这个方法
     */
    public RestResponse(int code, String msg) {
        this(code, msg, null);
    }

    /**
     * 一般业务不要直接使用这个方法
     */
    public RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResponse<Void> ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<>(Status.OK.code(), Status.OK.label(), data);
    }

    /**
     * 一般业务不要直接使用这个方法, 直接抛出异常即可
     */
    public static <T> RestResponse<T> fail(String msg) {
        return new RestResponse<>(Status.FAIL_OPERATION.code(), msg);
    }
}
