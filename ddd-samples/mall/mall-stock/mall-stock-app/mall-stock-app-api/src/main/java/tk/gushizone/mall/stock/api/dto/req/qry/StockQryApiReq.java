package tk.gushizone.mall.stock.api.dto.req.qry;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class StockQryApiReq {

    private List<Long> productIds;
}
