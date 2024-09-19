package tk.gushizone.infra.libs.core.rest.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tk.gushizone.infra.libs.base.constant.Orders;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;
import tk.gushizone.infra.libs.core.validation.constraints.Options;

@Data
@Schema(description = "排序项")
public class RestOrderEntry implements OrderEntry {

    @Schema(description = "排序列", example = "id")
    private String column;

    @Schema(description = "排序", example = "desc", allowableValues = {Orders.ASC, Orders.DESC})
    @Options(options = {Orders.ASC, Orders.DESC})
    private String order;
}
