package tk.gushizone.mall.order.adapter.out.repository.assembler;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.mall.order.adapter.out.repository.assembler.converter.OrderRepositoryConvertor;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;
import java.util.Map;

public class OrderRepositoryAssembler {

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
}
