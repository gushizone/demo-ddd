package tk.gushizone.mall.order.adapter.in.web.dto.req.qry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
@Accessors(chain = true)
public class OrderQryReq {

    @Schema(description = "ids")
    private List<Long> ids;

    @Schema(description = "列表")
    private List<Long> list;

}
