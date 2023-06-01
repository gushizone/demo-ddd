package tk.gushizone.infra.libs.core.web;

import tk.gushizone.infra.libs.core.common.BaseEnum;

/**
 * 状态码枚举
 * <p>
 * 业务仅使用 OK(2000) 和 FAIL_OPERATION(4006) 即可
 * <p>
 * 注意: 部分状态码 和 http status 存在映射
 * - 4XXX -> 4XX http status
 * - 5XXX -> 5XX http status
 * - other -> 200 http status
 * 参考: <a href="https://github.com/dibo-software/diboot/blob/develop/diboot-core/src/main/java/com/diboot/core/vo/Status.java">diboot:Status.java</a>
 *
 * @author gushizone
 * @see cn.hutool.http.HttpStatus
 * @since 2023/6/1
 */
public enum Status implements BaseEnum {

    /***
     * 请求处理成功
     */
    OK(2000, "成功"),
    /***
     * 部分成功
     * 备注: 一般用于批量处理场景
     */
    WARN_PARTIAL_SUCCESS(1001, "部分成功"),
    /***
     * 传入参数不对
     * 备注: 一般由框架统一处理
     */
    FAIL_INVALID_PARAM(4000, "请求参数不匹配"),
    /***
     * Token无效或已过期
     * 备注: 一般由框架统一处理
     */
    FAIL_INVALID_TOKEN(4001, "Token无效或已过期"),
    /***
     * 没有权限执行该操作
     * 备注: 一般由框架统一处理
     */
    FAIL_NO_PERMISSION(4003, "无权执行该操作"),
    /***
     * 数据校验不通过
     * 备注: 一般由框架统一处理, 特殊校验业务可使用
     */
    FAIL_VALIDATION(4005, "数据校验不通过"),
    /***
     * 操作执行失败
     * 备注: 业务异常,主动使用
     */
    FAIL_OPERATION(4006, "操作执行失败"),
    /***
     * 系统异常
     * 备注: 未知异常, 一般由框架统一处理
     */
    FAIL_EXCEPTION(5000, "系统异常"),
    /**
     * 服务不可用
     * 备注: 未知异常, 一般由框架统一处理
     */
    FAIL_SERVICE_UNAVAILABLE(5003, "服务不可用");;

    private int code;

    private String label;

    Status(int code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }


}
