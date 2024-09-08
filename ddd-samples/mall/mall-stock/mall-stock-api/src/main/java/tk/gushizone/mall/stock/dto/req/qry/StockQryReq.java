package tk.gushizone.mall.stock.dto.req.qry;

import lombok.Data;

import java.util.List;

@Data
public class StockQryReq {

    private List<Long> productIds;
}
