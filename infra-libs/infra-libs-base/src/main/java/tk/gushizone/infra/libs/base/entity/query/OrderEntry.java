package tk.gushizone.infra.libs.base.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import tk.gushizone.infra.libs.base.constant.Orders;
import tk.gushizone.infra.libs.base.validation.constraints.Options;

/**
 * @author gushizone
 * @since 2024/9/19
 */
@Getter
@Setter
@Schema(description = "排序项")
public class OrderEntry {

    @Schema(description = "排序列", example = "id")
    private String column;

    @Schema(description = "排序", example = "desc", allowableValues = {Orders.ASC, Orders.DESC})
    @Options(options = {Orders.ASC, Orders.DESC})
    private String order;
}
