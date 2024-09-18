package tk.gushizone.infra.libs.core.rest.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;

import jakarta.validation.constraints.Pattern;

@Data
@Schema(description = "排序项")
public class RestOrderEntry implements OrderEntry {

    @Schema(description = "排序列")
    private String column;

    /**
     * todo 枚举校验
     */
    @Schema(description = "排序", allowableValues = {"asc", "desc"})
    @Pattern(regexp = "asc|desc")
    private String order;
}
