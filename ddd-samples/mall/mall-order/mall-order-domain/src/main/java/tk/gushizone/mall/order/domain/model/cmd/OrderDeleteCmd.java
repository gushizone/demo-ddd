package tk.gushizone.mall.order.domain.model.cmd;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.event.OrderDeletedEvent;

import java.util.List;
import java.util.Map;

/**
 * todo 通用抽取
 *
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
@Accessors(chain = true)
public class OrderDeleteCmd {

    private List<RevisionRecord> records;

    private List<OrderAggregate> orderAggList;

    public List<OrderDeletedEvent> exec() {
        if (CollectionUtils.isEmpty(orderAggList)) {
            return Lists.newArrayList();
        }
        Map<Long, OrderAggregate> orderAggMap = ModelUtils.toMap(orderAggList, e -> e.getRoot().getId());
        List<OrderDeletedEvent> results = Lists.newArrayListWithExpectedSize(orderAggList.size());
        for (RevisionRecord item : records) {
            OrderDeletedEvent result = new OrderDeletedEvent();

            OrderAggregate orderAggregate = orderAggMap.get(item.getId());
            result.setOrder(orderAggregate.getRoot());
            result.setOrderItems(orderAggregate.getOrderItems());
            results.add(result);
        }
        return results;
    }
}
