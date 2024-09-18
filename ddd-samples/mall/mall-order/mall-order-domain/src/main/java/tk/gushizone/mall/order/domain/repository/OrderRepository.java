package tk.gushizone.mall.order.domain.repository;

import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
public interface OrderRepository {

    Long save(OrderCreateCmdResult orderCreateCmdResult);

    PagedResult<OrderAggregate> query(PagingParam<OrderQry> param);

    void delete(OrderDeleteCmd orderDeleteCmd);
}
