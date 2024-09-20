package tk.gushizone.mall.order.adapter.out.repository.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.mall.order.adapter.out.repository.assembler.converter.OrderItemRepositoryConvertor;
import tk.gushizone.mall.order.adapter.out.repository.assembler.converter.OrderRepositoryConvertor;
import tk.gushizone.mall.order.adapter.out.repository.dto.OrderCreateDmResult;
import tk.gushizone.mall.order.adapter.out.repository.dto.OrderDeleteDmResult;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.model.cmd.OrderDeleteCmdResult;
import tk.gushizone.mall.order.infrastructure.repository.db.po.Order;
import tk.gushizone.mall.order.infrastructure.repository.db.po.OrderItem;

import java.util.List;
import java.util.Map;

public class OrderRepositoryAssembler {

    public static OrderCreateDmResult toDmResult(OrderCreateCmdResult orderCreateCmdResult) {
        OrderCreateDmResult result = new OrderCreateDmResult();
        result.setOrder(OrderRepositoryConvertor.INSTANCE.toPo(orderCreateCmdResult.getOrder()));
        result.setOrderItems(OrderItemRepositoryConvertor.INSTANCE.toPoList(orderCreateCmdResult.getOrderItems()));
        return result;
    }

    public static List<OrderAggregate> toAgg(List<Order> orders,
                                             Map<Long, List<OrderItem>> orderToItemMap) {
        if (CollectionUtils.isEmpty(orders)) {
            return Lists.newArrayList();
        }
        List<OrderAggregate> results = Lists.newArrayListWithExpectedSize(orders.size());
        for (Order order : orders) {
            OrderAggregate result = OrderRepositoryConvertor.INSTANCE.toAgg(order);

            result.setOrderItems(OrderRepositoryConvertor.INSTANCE.toEntity(orderToItemMap.getOrDefault(order.getId(), Lists.newArrayList())));

            results.add(result);
        }
        return results;
    }

    public static List<OrderDeleteDmResult> toDmResult(List<OrderDeleteCmdResult> orderDeleteCmdResults) {
        if (CollectionUtils.isEmpty(orderDeleteCmdResults)) {
            return Lists.newArrayList();
        }
        List<OrderDeleteDmResult> results = Lists.newArrayListWithExpectedSize(orderDeleteCmdResults.size());
        for (OrderDeleteCmdResult cmd : orderDeleteCmdResults) {
            OrderDeleteDmResult result = new OrderDeleteDmResult();
            result.setOrder(OrderRepositoryConvertor.INSTANCE.toPo(cmd.getOrder()));
            result.setOrderItems(OrderItemRepositoryConvertor.INSTANCE.toPo(cmd.getOrderItems()));
            results.add(result);
        }
        return results;
    }
}
