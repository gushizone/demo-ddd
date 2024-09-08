package tk.gushizone.mall.stock.application.assembler;

import tk.gushizone.mall.stock.application.assembler.convertor.StockConverter;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.infrastructure.repository.db.po.Stock;

import java.util.List;

public class StockFactory {

    public static List<StockAggregate> build(List<Stock> stocks) {
        return StockConverter.INSTANCE.toAgg(stocks);
    }
}
