package tk.gushizone.infra.libs.base.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tk.gushizone.infra.libs.base.enums.BaseDict;
import tk.gushizone.infra.libs.base.validation.validator.DictValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典
 *
 * @author gushizone
 * @since 2024/9/19
 */
@Constraint(validatedBy = DictValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    /**
     * 字典类
     */
    Class<? extends BaseDict> dict();

    /**
     * 字典类别
     */
    int type();

    String message() default "{tk.gushizone.infra.libs.base.validation.constraints.Dict}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
