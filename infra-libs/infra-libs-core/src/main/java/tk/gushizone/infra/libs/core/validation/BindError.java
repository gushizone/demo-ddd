package tk.gushizone.infra.libs.core.validation;

import lombok.Data;

/**
 * @author gushizone
 * @since 2024/9/11
 */
@Data
public class BindError {

    private String field;

    private String fieldLabel;

    private String message;
}
