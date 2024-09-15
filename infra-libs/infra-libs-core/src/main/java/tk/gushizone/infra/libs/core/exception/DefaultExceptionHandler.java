package tk.gushizone.infra.libs.core.exception;

import com.google.common.base.Throwables;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.core.rest.Status;
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
     * - 注意: spring 会自动转换 ConstraintViolationException
     * - MethodArgumentNotValidException: 参数校验异常
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public RestResponse<?> bindExceptionHandler(HttpServletRequest request, Exception ex) {
        return handle(ExceptionHandles.BIND_EXCEPTION, ex, () -> {
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
     * - HttpRequestMethodNotSupportedException: http方法未匹配
     * - HttpMessageNotReadableException: 反序列化异常, 如数据类型不匹配
     * - MethodArgumentTypeMismatchException: 方法参数类型不匹配, 如/{id}, 数据类型不匹配
     */
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    public RestResponse<?> badRequestException(Exception ex) {
        return handle(ExceptionHandles.BAD_REQUEST, ex, () -> RestResponse.fail(BizException.of(Status.FAIL_BAD_REQUEST)));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(Exception.class)
    public RestResponse<?> bizExceptionHandler(Exception ex) {
        return handle(ExceptionHandles.BIZ_EXCEPTION, ex, () -> {
            Throwable rootCause = Throwables.getRootCause(ex);
            if (rootCause instanceof BizException) {
                return RestResponse.fail((BizException) rootCause);
            }
            // 未知异常
            throw new RuntimeException(ex);
        });
    }

    /**
     * 异常处理流程
     */
    protected RestResponse<?> handle(ExceptionHandles exHandles, Exception ex, Supplier<RestResponse<?>> handler) {
        try {
            log.atLevel(exHandles.getLevel())
                    .log("{}: {}", exHandles.getLabel(), ex.getMessage(), ex);
            return handler.get();
        } catch (Exception exception) {
            Throwable rootCause = Throwables.getRootCause(exception);
            log.atLevel(ExceptionHandles.FAIL_EXCEPTION.getLevel())
                    .log("{}: {}", ExceptionHandles.FAIL_EXCEPTION.getLabel(), rootCause.getMessage(), rootCause);
            return RestResponse.fail(BizException.of(Status.FAIL_EXCEPTION));
        }
    }
}