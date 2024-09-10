package tk.gushizone.infra.libs.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tk.gushizone.infra.libs.base.exception.BusinessException;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.base.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常统一处理的默认实现
 *
 * @author gushizone
 * @since 2023/6/2
 */
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 统一处理校验错误 BindResult
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Object validExceptionHandler(Exception ex) {
        BindingResult br = null;
        if (ex instanceof BindException) {
            br = ((BindException) ex).getBindingResult();
        }
        RestResponse<?> restResponse = null;
        if (br != null && br.hasErrors()) {

//            String validateErrorMsg = V.getBindingError(br);
            restResponse = new RestResponse<>()
                    .setCode(Status.FAIL_VALIDATION.code())
                    .setMsg(Status.FAIL_VALIDATION.label()) // todo
//                    .data() todo
                     ;
//            log.warn("数据校验失败, {}: {}", br.getObjectName(), validateErrorMsg);
        }
        return new ResponseEntity<>(restResponse, HttpStatus.OK);
    }

    /**
     * 统一异常处理类 todo
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, Exception e) {
        log.warn("请求处理异常", e);
        if (e instanceof BusinessException) {
            return new ResponseEntity<>(RestResponse.fail((BusinessException) e), HttpStatus.OK);
        } else if (e.getCause() instanceof BusinessException) {
            return new ResponseEntity<>(RestResponse.fail((BusinessException) e.getCause()), HttpStatus.OK);
        }
        return new ResponseEntity<>(RestResponse.fail(new BusinessException(Status.FAIL_EXCEPTION)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 构建 response msg 内容
     */
    protected String buildMsg(HttpStatus status, Exception e) {
        // 返回最原始的异常信息
        return e == null ? status.getReasonPhrase() : getRootCauseMessage(e);
    }

    /**
     * 获取状态码
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 返回最开始的异常信息
     */
    public static String getRootCauseMessage(Throwable t) {
        List<Throwable> list = ExceptionUtils.getThrowableList(t);
        Assert.notEmpty(list, () -> "没有异常信息");
        return list.get(list.size() - 1).getMessage();
    }
}