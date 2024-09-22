package tk.gushizone.mall.order.domain.service.handler;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.entity.BaseAggregate;
import tk.gushizone.infra.libs.base.entity.CommandParam;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderDeleteCmd;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @since 2024/9/22
 */
public class OrderDeleteCmdHandler {

    public static List<OrderAggregate> handle(OrderDeleteCmd orderDeleteCmd) {
        if (CollectionUtils.isEmpty(orderDeleteCmd.getOrderAggregates())) {
            return Lists.newArrayList();
        }
        Map<Long, OrderAggregate> orderAggMap = ModelUtils.toMap(orderDeleteCmd.getOrderAggregates(), BaseAggregate::getId);
        List<OrderAggregate> results = Lists.newArrayListWithExpectedSize(orderDeleteCmd.getRecords().size());
        for (CommandParam item : orderDeleteCmd.getRecords()) {
            OrderAggregate result = orderAggMap.get(item.getId());
            if (result == null) {
                continue;
            }
            result.setVersion(item.getVersion());
            results.add(result);
        }
        return results;
    }
}
