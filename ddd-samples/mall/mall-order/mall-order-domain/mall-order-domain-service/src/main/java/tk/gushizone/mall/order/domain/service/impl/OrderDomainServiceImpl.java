package tk.gushizone.mall.order.domain.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.CommandParam;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.qry.OrderQry;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.order.domain.service.handler.OrderCreateCmdHandler;
import tk.gushizone.mall.order.domain.service.handler.OrderDeleteCmdHandler;

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
        OrderAggregate orderAggregate  = OrderCreateCmdHandler.handle(orderCreateCmd);

        return orderRepository.save(orderAggregate);
    }

    @Override
    public PageableResult<OrderAggregate> query(PageableParam<OrderQry> pageableParam) {
        return orderRepository.query(pageableParam);
    }

    @Override
    public void delete(OrderDeleteCmd orderDeleteCmd) {

        List<Long> ids = ModelUtils.map(orderDeleteCmd.getRecords(), CommandParam::getId);
        List<OrderAggregate> orderAggList = orderRepository.query(PageableParam.of(new OrderQry().setIds(ids))).getRecords();
        if (CollectionUtils.isEmpty(orderAggList)) {
            return;
        }
        orderDeleteCmd.setOrderAggregates(orderAggList);

        List<OrderAggregate> orderAggregates = OrderDeleteCmdHandler.handle(orderDeleteCmd);

        orderRepository.delete(orderAggregates);
    }
}
