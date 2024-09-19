package tk.gushizone.mall.order.adapter.out.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.rest.query.Pages;
import tk.gushizone.mall.order.adapter.out.repository.assembler.OrderRepositoryAssembler;
import tk.gushizone.mall.order.adapter.out.repository.dto.OrderCreateDmResult;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.infrastructure.repository.db.mapper.OrderItemMapper;
import tk.gushizone.mall.order.infrastructure.repository.db.mapper.OrderMapper;
import tk.gushizone.mall.order.infrastructure.repository.db.po.Order;
import tk.gushizone.mall.order.infrastructure.repository.db.po.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @since 2022/10/18 17:46
 */
@Repository
public class OrderDbRepository implements OrderRepository {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(OrderCreateCmdResult orderCreateCmdResult) {

        OrderCreateDmResult result = OrderRepositoryAssembler.toDmResult(orderCreateCmdResult);

        orderMapper.insert(result.getOrder());

        result.linking();
        orderItemMapper.insert(result.getOrderItems());

        return result.getOrder().getId();
    }

    @Override
    public PagedResult<OrderAggregate> query(PagingParam<OrderQry> PagingParam) {
        OrderQry orderQry = PagingParam.getParam();

        Page<Order> orderPage = orderMapper.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(orderQry.getIds()), Order::getId, orderQry.getIds())
                .page(Pages.toPage(PagingParam.getPage()));
        if (CollectionUtils.isEmpty(orderPage.getRecords())) {
            return Pages.toResult(orderPage);
        }
        List<Long> orderIds = ModelUtils.map(orderPage.getRecords(), Order::getId);

        List<OrderItem> orderItems = orderItemMapper.lambdaQuery()
                .in(OrderItem::getOrderId, orderIds)
                .list();
        Map<Long, List<OrderItem>> orderToItemMap = ModelUtils.groupBy(orderItems, OrderItem::getOrderId);

        List<OrderAggregate> orders = OrderRepositoryAssembler.toAgg(orderPage.getRecords(), orderToItemMap);
        return Pages.toResult(orderPage, orders);
    }

    /**
     * 版本控制，应该通过po更新 todo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(OrderDeleteCmd orderDeleteCmd) {

        orderMapper.deleteByIds(orderDeleteCmd.getRecords());

        List<OrderItem> orderItems = orderItemMapper.lambdaQuery()
                .in(OrderItem::getOrderId, orderDeleteCmd.getRecords())
                .list();
        orderItemMapper.deleteByIds(orderItems);
    }
}
