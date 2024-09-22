package tk.gushizone.mall.order.domain.service.impl;

import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.CommandRecord;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.enums.OrderErrors;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderModifyCmd;
import tk.gushizone.mall.order.domain.service.dto.qry.OrderQry;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.order.domain.service.handler.OrderCreateCmdHandler;
import tk.gushizone.mall.order.domain.service.handler.OrderDeleteCmdHandler;
import tk.gushizone.mall.order.domain.service.handler.OrderModifyCmdHandler;

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
        OrderAggregate createResult  = OrderCreateCmdHandler.handle(orderCreateCmd);

        return orderRepository.create(createResult);
    }

    @Override
    public PageableResult<OrderAggregate> query(PageableParam<OrderQry> pageableParam) {
        return orderRepository.query(pageableParam);
    }

    @Override
    public Long modify(OrderModifyCmd cmd) {

        OrderQry orderQry = new OrderQry().setIds(Lists.newArrayList());
        OrderAggregate orderAggregate = orderRepository.query(PageableParam.of(orderQry)).findFirst();
        if (orderAggregate == null) {
            throw BizException.of(OrderErrors.ORDER_NOT_FOUND);
        }
        OrderAggregate modifyResult =  OrderModifyCmdHandler.handle(cmd);

        orderRepository.modify(modifyResult);

        return orderAggregate.getId();
    }

    @Override
    public void delete(OrderDeleteCmd cmd) {

        List<Long> ids = ModelUtils.map(cmd.getRecords(), CommandRecord::getId);
        List<OrderAggregate> orderAggList = orderRepository.query(PageableParam.of(new OrderQry().setIds(ids))).getRecords();
        if (CollectionUtils.isEmpty(orderAggList)) {
            return;
        }
        cmd.setOrderAggregates(orderAggList);

        List<OrderAggregate> deleteResults = OrderDeleteCmdHandler.handle(cmd);

        orderRepository.delete(deleteResults);
    }
}
