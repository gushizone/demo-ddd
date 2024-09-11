package tk.gushizone.mall.stock.domain.repository;

import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface StockRepository {

    void save(StockAggregate stock);

    List<StockAggregate> query(StockQry stockQry);
}
