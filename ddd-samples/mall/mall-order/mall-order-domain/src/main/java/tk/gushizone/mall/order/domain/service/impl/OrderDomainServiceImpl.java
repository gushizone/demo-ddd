package tk.gushizone.mall.order.domain.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.domain.service.OrderDomainService;

import jakarta.annotation.Resource;

/**
 * @author gushizone
 * @since 2022/10/18 17:47
 */
@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public Long create(OrderCreateCmd orderCreateCmd) {

        // todo 查询领域内的其他数据

        OrderCreateCmdResult orderCreateCmdResult = orderCreateCmd.exec();

        return orderRepository.save(orderCreateCmdResult);
    }

    @Override
    public PagedResult<OrderAggregate> query(PagingParam<OrderQry> pagingParam) {

        PagedResult<OrderAggregate> orderPagedResult =  orderRepository.query(pagingParam);

        return orderPagedResult;
    }
}
