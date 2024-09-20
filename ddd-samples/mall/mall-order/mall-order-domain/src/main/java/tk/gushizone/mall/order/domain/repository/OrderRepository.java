package tk.gushizone.mall.order.domain.repository;

import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.event.OrderCreatedEvent;
import tk.gushizone.mall.order.domain.model.event.OrderDeletedEvent;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface OrderRepository {

    Long save(OrderCreatedEvent orderCreatedEvent);

    PagedResult<OrderAggregate> query(PagingParam<OrderQry> param);

    void delete(List<OrderDeletedEvent> orderDeletedEvents);

    void delete(OrderDeletedEvent orderDeletedEvent);
}
