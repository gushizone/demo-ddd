package tk.gushizone.mall.order.adapter.out.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.mybatisplus.Pages;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.infrastructure.repository.db.mapper.OrderItemMapper;
import tk.gushizone.mall.order.infrastructure.repository.db.mapper.OrderMapper;

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
    public Long save(OrderAggregate orderAggregate) {

        orderMapper.insert(orderAggregate.getRoot());
        // 关联子项
        orderAggregate.linking();
        orderItemMapper.insert(orderAggregate.getOrderItems());
        return orderAggregate.getId();
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

        return Pages.toResult(orderPage, OrderAggregate.of(orderPage.getRecords(), orderToItemMap));
    }

    @Override
    public void delete(List<OrderAggregate> orderAggregates) {

        for (OrderAggregate orderAggregate : orderAggregates) {
            delete(orderAggregate);
        }
    }

    @Override
    public void delete(OrderAggregate orderAggregate) {

        orderMapper.deleteById(orderAggregate.getRoot());

        orderItemMapper.deleteByIds(orderAggregate.getOrderItems());
    }
}
