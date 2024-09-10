package tk.gushizone.mall.stock.application.assembler;

import tk.gushizone.mall.stock.application.assembler.converter.StockConverter;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.dto.req.qry.StockQryReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

public class StockAppAssembler {

    public static StockQry toQry(StockQryReq req) {
        StockQry stockQry = new StockQry();
        stockQry.setProductIds(req.getProductIds());
        return stockQry;
    }

    public static List<StockApiRsp> toApi(List<StockAggregate> stocks) {
        return StockConverter.INSTANCE.toApi(stocks);
    }
}
