package tk.gushizone.infra.libs.core.web;


import lombok.Data;

import java.io.Serializable;

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
}
