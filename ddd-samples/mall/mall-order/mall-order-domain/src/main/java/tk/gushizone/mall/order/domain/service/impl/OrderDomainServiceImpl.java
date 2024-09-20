package tk.gushizone.mall.order.domain.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.event.OrderCreatedEvent;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.event.OrderDeletedEvent;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.domain.service.OrderDomainService;

import java.util.List;

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

        // 可以查询领域内的其他数据，放入 cmd 中
        OrderCreatedEvent orderCreatedEvent = orderCreateCmd.exec();

        return orderRepository.save(orderCreatedEvent);
    }

    @Override
    public PagedResult<OrderAggregate> query(PagingParam<OrderQry> PagingParam) {
        return orderRepository.query(PagingParam);
    }

    @Override
    public void delete(OrderDeleteCmd orderDeleteCmd) {

        List<Long> ids = ModelUtils.map(orderDeleteCmd.getRecords(), RevisionRecord::getId);
        List<OrderAggregate> orderAggList = orderRepository.query(PagingParam.of(new OrderQry().setIds(ids))).getRecords();
        if (CollectionUtils.isEmpty(orderAggList)) {
            return;
        }
        orderDeleteCmd.setOrderAggList(orderAggList);

        List<OrderDeletedEvent> orderDeletedEvents = orderDeleteCmd.exec();

        orderRepository.delete(orderDeletedEvents);
    }
}
