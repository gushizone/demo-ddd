package tk.gushizone.mall.order.domain.repository;

import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.qry.OrderQry;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface OrderRepository {

    Long save(OrderAggregate orderAggregate);

    PageableResult<OrderAggregate> query(PageableParam<OrderQry> param);

    void delete(List<OrderAggregate> orderAggregates);

    void delete(OrderAggregate orderAggregate);
}
