package tk.gushizone.mall.stock.adapter.out.repository.assembler;

import tk.gushizone.mall.stock.application.assembler.converter.StockConverter;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.infrastructure.repository.db.po.Stock;

import java.util.List;

public class StockRepositoryAssembler {

    public static List<StockAggregate> toAgg(List<Stock> stocks) {
        return StockConverter.INSTANCE.toAgg(stocks);
    }

}
