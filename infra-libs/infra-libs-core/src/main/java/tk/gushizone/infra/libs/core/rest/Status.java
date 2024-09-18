package tk.gushizone.infra.libs.core.rest;

import tk.gushizone.infra.libs.base.enums.BaseEnum;

/**
 * 响应码枚举
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
     */
    WARN_PARTIAL_SUCCESS(2001, "部分成功"),
    /***
     * 请求参数、格式或语法不正确等
     */
    FAIL_BAD_REQUEST(4000, "错误的请求"),
    /***
     * 认证不通过 : 缺少有效身份信息的统称, 在不需要区分或没有细分项时使用
     */
    FAIL_INVALID_IDENTIFICATION(4001, "认证不通过"),
    /***
     * 认证不通过 - Token无效或已过期
     * todo - 一般会伴随重定向
     */
    FAIL_INVALID_TOKEN(4101, "Token无效或已过期"),
    /***
     * 认证不通过 - 用户名或密码错误
     */
    FAIL_INVALID_USERNAME_OR_PASSWORD(4201, "用户名或密码错误"),
    /***
     * 认证不通过 - 验证码(人机验证, 包括文字验证码, 声音验证码, 图像选择验证码,滑块验证码等)
     */
    FAIL_INVALID_CAPTCHA(4301, "验证码错误"),
    /***
     * 认证不通过 - 短信验证码
     */
    FAIL_INVALID_SMS_VERIFICATION_CODE(4401, "短信验证码错误"),
    /**
     * 认证不通过 - 多因素认证(MFA)
     */
    FAIL_INVALID_MFA(4901, "多因素认证失败"),
    /***
     * 没有权限执行该操作
     */
    FAIL_NO_PERMISSION(4003, "无权执行该操作"),
    /***
     * 数据校验不通过
     */
    FAIL_VALIDATION(4005, "数据校验不通过"),
    /***
     * 操作执行失败 : 业务异常
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

    private final int code;

    private final String label;

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
