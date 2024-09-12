package tk.gushizone.mall.order.application.dto.req.qry;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * todo
 *
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
public class OrderQryReq {

    @Schema(description = "ids")
    private List<Long> ids;

    @Schema(description = "列表")
    @Size(min = 1, max = 20)
    private List<Long> list;

}
