package tk.gushizone.infra.libs.base.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tk.gushizone.infra.libs.base.enums.BaseDict;
import tk.gushizone.infra.libs.base.enums.Enums;
import tk.gushizone.infra.libs.base.validation.constraints.Dict;

/**
 * 字典校验
 *
 * @author gushizone
 * @since 2024/9/19
 */
public class DictValidator implements ConstraintValidator<Dict, Integer> {

    private Class<? extends BaseDict> dict;

    int type;

    @Override
    public void initialize(Dict constraintAnnotation) {
        dict = constraintAnnotation.dict();
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Enums.codeOf(dict, type, value) != null;
    }
}
