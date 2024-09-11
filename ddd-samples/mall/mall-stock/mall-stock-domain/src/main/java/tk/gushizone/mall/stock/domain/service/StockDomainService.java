package tk.gushizone.mall.stock.domain.service;

import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 17:47
 */
public interface StockDomainService {

    List<StockAggregate> query(StockQry stockQry);
}
