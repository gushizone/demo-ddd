package tk.gushizone.mall.order.adapter.out.repository.db;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.mybatisplus.Pages;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.domain.service.dto.qry.OrderQry;
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
    public Long create(OrderAggregate createResult) {

        orderMapper.insert(createResult.getRoot());
        // 关联子项
        createResult.linking();
        orderItemMapper.insert(createResult.getOrderItems());
        return createResult.getId();
    }

    @Override
    public PageableResult<OrderAggregate> query(PageableParam<OrderQry> pageableParam) {
        OrderQry orderQry = pageableParam.getParam();

        Page<Order> orderPage = orderMapper.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(orderQry.getIds()), Order::getId, orderQry.getIds())
                .page(Pages.toPage(pageableParam.getPage()));
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
    public void modify(OrderAggregate modifyResult) {

        orderMapper.updateById(modifyResult.getRoot());
    }

    @Override
    public void delete(List<OrderAggregate> deleteResults) {

        for (OrderAggregate orderAggregate : deleteResults) {
            delete(orderAggregate);
        }
    }

    @Override
    public void delete(OrderAggregate deleteResult) {

        orderMapper.deleteById(deleteResult.getRoot());

        orderItemMapper.deleteByIds(deleteResult.getOrderItems());
    }
}
