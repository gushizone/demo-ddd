package tk.gushizone.infra.libs.core.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import tk.gushizone.infra.libs.core.mybatisplus.handler.AutoFillMetaObjectHandler;

/**
 * mybatis-plus 自动配置
 *
 * @author gushizone
 * @since 2024/9/15
 */
public class MpAutoConfiguration {

    /**
     * 自动填充基础属性
     */
    @Bean
    @ConditionalOnMissingBean
    public AutoFillMetaObjectHandler autoFillMetaObjectHandler() {
        return new AutoFillMetaObjectHandler();
    }

}
