package tk.gushizone.mall.order.domain.repository;

import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;

/**
 * @author gushizone
 * @date 2022/10/18 17:46
 */
public interface OrderRepository {

    Long save(OrderCreateCmdResult orderCreateCmdResult);

    PagedResult<OrderAggregate> query(PagingParam<OrderQry> param);
}
