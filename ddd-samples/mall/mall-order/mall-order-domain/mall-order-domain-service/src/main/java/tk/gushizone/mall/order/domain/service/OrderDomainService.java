package tk.gushizone.mall.order.domain.service;

import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.service.dto.qry.OrderQry;

/**
 * @author gushizone
 * @since 2022/10/18 17:47
 */
public interface OrderDomainService {

    Long create(OrderCreateCmd cmd);

    PageableResult<OrderAggregate> query(PageableParam<OrderQry> pageableParam);

    void delete(OrderDeleteCmd orderDeleteCmd);

}
