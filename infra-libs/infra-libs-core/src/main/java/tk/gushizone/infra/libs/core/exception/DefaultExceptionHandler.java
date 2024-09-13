package tk.gushizone.infra.libs.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tk.gushizone.infra.libs.base.Status;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.core.validation.BindFieldErrorHelper;

import java.util.function.Supplier;

/**
 * 全局异常统一处理的默认实现
 *
 * @author gushizone
 * @since 2023/6/2
 */
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 参数绑定异常
     * - spring 会自动转换 ConstraintViolationException
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public RestResponse<?> bindExceptionHandler(HttpServletRequest request, Exception ex) {
        return handle("参数绑定异常", ex, () -> {
            BindingResult bindingResult = null;
            if (ex instanceof BindException) {
                bindingResult = ((BindException) ex).getBindingResult();
            }
            return new RestResponse<>()
                    .setCode(Status.FAIL_VALIDATION.code())
                    .setMsg(Status.FAIL_VALIDATION.label())
                    .setData(BindFieldErrorHelper.getBindFieldErrors(bindingResult));
        });
    }

    /**
     * 错误请求异常
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMessageNotReadableException.class})
    public RestResponse<?> badRequestException(HttpServletRequest request, Exception ex) {
        return handle("错误请求", ex, () -> RestResponse.fail(new BizException(Status.FAIL_BAD_REQUEST)));
    }

    /**
     * 异常处理器
     */
    @ExceptionHandler(Exception.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest request, Exception ex) {
        return handle("请求处理异常", ex, () -> {
            if (ex instanceof BizException) {
                return RestResponse.fail((BizException) ex);
            } else if (ex.getCause() instanceof BizException) {
                return RestResponse.fail((BizException) ex.getCause());
            }
            return RestResponse.fail(new BizException(Status.FAIL_EXCEPTION));
        });
    }

    /**
     * 异常处理流程
     */
    protected RestResponse<?> handle(String exLabel, Exception ex, Supplier<RestResponse<?>> handler) {
        try {
            // todo 这里添加业务异常限制逻辑
            // todo 异常枚举控制日志打印
            log.info("{}: {}", exLabel, ex.getMessage(), ex);
            return handler.get();
        } catch (Exception exception) {
            log.error("异常处理失败: {}", exception.getMessage(), exception);
            return RestResponse.fail(new BizException(Status.FAIL_EXCEPTION));
        }
    }
}