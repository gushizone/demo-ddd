package tk.gushizone.infra.libs.core.rest.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tk.gushizone.infra.libs.base.query.OrderEntry;

import javax.validation.constraints.Pattern;

@Data
@Schema(description = "排序项")
public class RestOrderEntry implements OrderEntry {

    @Schema(description = "排序列")
    private String column;

    @Schema(description = "升序/降序", allowableValues = {"asc", "desc"})
    @Pattern(regexp = "asc|desc")
    private String order;
}
