package tk.gushizone.infra.libs.core.starter.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import tk.gushizone.infra.libs.core.exception.DefaultExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultExceptionHandler {

}
