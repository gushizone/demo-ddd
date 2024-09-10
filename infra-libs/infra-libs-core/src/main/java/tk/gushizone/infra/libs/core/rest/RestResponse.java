package tk.gushizone.infra.libs.core.rest;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import tk.gushizone.infra.libs.base.Status;
import tk.gushizone.infra.libs.base.exception.BusinessException;

import java.io.Serializable;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Data
@Accessors(chain = true)
@Schema(description = "服务响应")
public class RestResponse<T> implements Serializable {

    /**
     * 状态码
     */
    @Schema(description = "状态码", example = "200")
    private int code;
    /**
     * 消息
     */
    @Schema(description = "消息", example = "成功")
    private String msg;
    /**
     * 数据
     */
    @Schema(description = "数据")
    private T data;

    public static RestResponse<Void> ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<T>()
                .setCode(Status.OK.code())
                .setMsg(Status.OK.label())
                .setData(data);
    }

    /**
     * 一般业务不要直接使用这个方法, 直接抛出业务异常即可
     */
    public static <T> RestResponse<T> fail(String msg) {
        return new RestResponse<T>()
                .setCode(Status.FAIL_OPERATION.code())
                .setMsg(msg);
    }

    /**
     * 一般业务不要直接使用这个方法, 直接抛出业务异常即可
     */
    public static <T> RestResponse<T> fail(BusinessException ex) {
        return new RestResponse<T>()
                .setCode(ex.getCode())
                .setMsg(ex.getMessage());
    }
}
