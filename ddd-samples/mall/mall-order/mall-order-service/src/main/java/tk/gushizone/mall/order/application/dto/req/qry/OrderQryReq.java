package tk.gushizone.mall.order.application.dto.req.qry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
public class OrderQryReq {

    @Schema(description = "ids")
    private List<Long> ids;

    @Schema(description = "列表")
    private List<Long> list;

}
