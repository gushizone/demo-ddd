package tk.gushizone.infra.libs.core.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tk.gushizone.infra.libs.core.validation.validator.OptionsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可选项
 *
 * @author gushizone
 * @since 2024/9/19
 */
@Constraint(validatedBy = OptionsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Options {

    /**
     * 可选项
     */
    String[] options();

    String message() default "{tk.gushizone.infra.libs.core.validation.constraints.Options}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
