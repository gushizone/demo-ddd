package tk.gushizone.infra.libs.core.starter;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author gushizone
 * @since 2024/9/18
 */
public class SerialAutoConfiguration {

    /**
     * 自定义序列化操作
     * - Long -> String : 避免js精度缺失
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            SimpleModule simpleModule = new SimpleModule();
            // Long -> String
            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            builder.modules(simpleModule);
        };
    }


}
