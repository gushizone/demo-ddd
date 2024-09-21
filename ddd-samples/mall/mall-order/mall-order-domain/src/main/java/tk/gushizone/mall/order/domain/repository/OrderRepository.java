package tk.gushizone.mall.order.domain.repository;

import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface OrderRepository {

    Long save(OrderAggregate orderAggregate);

    PagedResult<OrderAggregate> query(PagingParam<OrderQry> param);

    void delete(List<OrderAggregate> orderAggregates);

    void delete(OrderAggregate orderAggregate);
}
