package tk.gushizone.mall.order.domain.model.cmd;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.entity.BaseAggregate;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;

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

    private List<OrderAggregate> orderAggregates;

    public List<OrderAggregate> exec() {
        if (CollectionUtils.isEmpty(orderAggregates)) {
            return Lists.newArrayList();
        }
        Map<Long, OrderAggregate> orderAggMap = ModelUtils.toMap(orderAggregates, BaseAggregate::getId);
        List<OrderAggregate> results = Lists.newArrayListWithExpectedSize(records.size());
        for (RevisionRecord item : records) {
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
