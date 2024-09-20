package tk.gushizone.mall.order.domain.service;

import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;

/**
 * @author gushizone
 * @since 2022/10/18 17:47
 */
public interface OrderDomainService {

    Long create(OrderCreateCmd cmd);

    PagedResult<OrderAggregate> query(PagingParam<OrderQry> PagingParam);

    void delete(OrderDeleteCmd orderDeleteCmd);

}
