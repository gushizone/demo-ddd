package tk.gushizone.mall.stock.infrastructure.repository.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.gushizone.infra.libs.core.mybatisplus.BasePlusMapper;
import tk.gushizone.mall.stock.infrastructure.repository.db.po.Stock;

@Mapper
public interface StockMapper extends BasePlusMapper<Stock> {

}
