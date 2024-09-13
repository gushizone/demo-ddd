package tk.gushizone.infra.libs.core.validation;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author gushizone
 * @since 2024/9/11
 */
@Slf4j
public class BindFieldErrorHelper {


    public static List<BindFieldError> getBindFieldErrors(BindingResult bindingResult) {
        if (bindingResult == null || !bindingResult.hasFieldErrors()) {
            return Lists.newArrayList();
        }
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<BindFieldError> results = Lists.newArrayListWithExpectedSize(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            BindFieldError result = new BindFieldError();
            result.setField(fieldError.getField());
            result.setMessage(toMessage(fieldError.getDefaultMessage()));
            result.setFieldLabel(toFieldLabel(fieldError));
            results.add(result);
        }
        return results;
    }

    private static String toMessage(String defaultMessage) {
        if (StringUtils.isEmpty(defaultMessage)) {
            return StringUtils.EMPTY;
        }
        return defaultMessage.replaceAll("\"", "'");
    }

    private static String toFieldLabel(FieldError fieldError) {
        try {
            ConstraintViolation<?> violation = fieldError.unwrap(ConstraintViolation.class);
            Class<?> fieldClazz = violation.getLeafBean().getClass();
            Path.Node fieldNode = CollectionUtil.getLast(IterUtil.toList(violation.getPropertyPath()));
            Field field = ReflectUtil.getField(fieldClazz, fieldNode.getName());
            Schema schema = field.getAnnotation(Schema.class);
            if (schema == null) {
                log.warn("未标注字段描述, 请使用: {}#description()", Schema.class.getName());
                return null;
            }
            return schema.description();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }
}
