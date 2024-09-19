package tk.gushizone.mall.stock.domain.service;

import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;

/**
 * @author gushizone
 * @since 2022/10/18 17:47
 */
public interface StockDomainService {

    PagedResult<StockAggregate> query(PagingParam<StockQry> IPagingParam);
}
