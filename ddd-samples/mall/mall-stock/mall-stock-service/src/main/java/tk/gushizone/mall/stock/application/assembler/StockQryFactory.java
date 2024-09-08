package tk.gushizone.mall.stock.application.assembler;

import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.dto.req.qry.StockQryReq;

public class StockQryFactory {


    public static StockQry build(StockQryReq req) {
        StockQry stockQry = new StockQry();
        stockQry.setProductIds(req.getProductIds());
        return stockQry;
    }
}
