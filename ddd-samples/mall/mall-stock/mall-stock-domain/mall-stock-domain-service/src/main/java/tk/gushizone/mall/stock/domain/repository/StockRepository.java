package tk.gushizone.mall.stock.domain.repository;

import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface StockRepository {

    PageableResult<StockAggregate> query(PageableParam<StockQry> pageableParam);

    void save(StockAggregate stock);

}
