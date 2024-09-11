package tk.gushizone.mall.order.application.dto.req.qry;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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
    @NotEmpty(message = "id is empty")
    private List<Long> ids;

}
