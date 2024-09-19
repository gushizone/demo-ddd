package tk.gushizone.infra.libs.core.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import tk.gushizone.infra.libs.core.validation.constraints.Options;

import java.util.Arrays;

/**
 * 可选项校验
 *
 * @author gushizone
 * @since 2024/9/19
 */
public class OptionsValidator implements ConstraintValidator<Options, String> {

    private String[] options = {};

    @Override
    public void initialize(Options constraintAnnotation) {
        options = constraintAnnotation.options();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        return Arrays.asList(options).contains(value);
    }
}
