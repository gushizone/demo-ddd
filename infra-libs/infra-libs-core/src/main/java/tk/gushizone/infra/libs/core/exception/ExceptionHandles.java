package tk.gushizone.infra.libs.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.event.Level;

@Getter
@AllArgsConstructor
public enum ExceptionHandles {

    BIND_EXCEPTION("参数绑定异常", Level.INFO),
    BAD_REQUEST("错误请求", Level.WARN),
    BIZ_EXCEPTION("业务异常", Level.WARN),
    FAIL_EXCEPTION("服务器异常", Level.ERROR),

    ;

    private final String label;

    private final Level level;
}
