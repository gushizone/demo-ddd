package tk.gushizone.infra.libs.core.starter;

import org.springframework.context.annotation.Import;
import tk.gushizone.infra.libs.core.starter.exception.GlobalExceptionHandler;

@Import(GlobalExceptionHandler.class)
public class ExceptionAutoConfiguration {

}
