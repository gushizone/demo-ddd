package tk.gushizone.mall.stock.dto.req.qry;

import lombok.Data;

import java.util.List;

@Data
public class StockQryApiReq {

    private List<Long> productIds;
}
