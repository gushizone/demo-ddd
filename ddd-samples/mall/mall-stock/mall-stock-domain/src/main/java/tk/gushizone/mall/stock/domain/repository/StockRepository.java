package tk.gushizone.mall.stock.domain.repository;

import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface StockRepository {

    PagedResult<StockAggregate> query(PagingParam<StockQry> pagingParam);

    void save(StockAggregate stock);

}
