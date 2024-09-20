package tk.gushizone.mall.order.domain.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingData;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmdResult;
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

        // todo 查询领域内的其他数据

        OrderCreateCmdResult orderCreateCmdResult = orderCreateCmd.exec();

        return orderRepository.save(orderCreateCmdResult);
    }

    @Override
    public PagedResult<OrderAggregate> query(PagingParam<OrderQry> PagingParam) {

        PagedResult<OrderAggregate> orderIPagedResult = orderRepository.query(PagingParam);

        return orderIPagedResult;
    }

    @Override
    public void delete(OrderDeleteCmd orderDeleteCmd) {

        List<Long> ids = ModelUtils.map(orderDeleteCmd.getRecords(), RevisionRecord::getId);
        List<OrderAggregate> orderAggList = orderRepository.query(PagingParam.of(new OrderQry().setIds(ids))).getRecords();
        if (CollectionUtils.isEmpty(orderAggList)) {
            return;
        }
        List<OrderDeleteCmdResult> orderDeleteCmdResults = orderDeleteCmd.exec(orderAggList);

        orderRepository.delete(orderDeleteCmdResults);
    }
}
