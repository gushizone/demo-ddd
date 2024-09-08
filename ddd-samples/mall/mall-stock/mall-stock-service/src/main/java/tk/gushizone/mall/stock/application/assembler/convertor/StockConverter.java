package tk.gushizone.mall.stock.application.assembler.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;
import tk.gushizone.mall.stock.infrastructure.repository.db.po.Stock;

import java.util.List;

@Mapper
public interface StockConverter {

    StockConverter INSTANCE = Mappers.getMapper(StockConverter.class);

    List<StockAggregate> toAgg(List<Stock> stocks);

    List<StockApiRsp> toApi(List<StockAggregate> stocks);
}
