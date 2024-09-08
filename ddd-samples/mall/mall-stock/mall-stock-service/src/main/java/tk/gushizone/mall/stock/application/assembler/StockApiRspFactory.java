package tk.gushizone.mall.stock.application.assembler;

import tk.gushizone.mall.stock.application.assembler.convertor.StockConverter;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

public class StockApiRspFactory {

    public static List<StockApiRsp> build(List<StockAggregate> stocks) {
        return StockConverter.INSTANCE.toApi(stocks);
    }
}
