package tk.gushizone.mall.order.domain.model.cmd;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;

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

    public List<OrderDeleteCmdResult> exec(List<OrderAggregate> orderAggList) {
        if (CollectionUtils.isEmpty(orderAggList)) {
            return Lists.newArrayList();
        }
        Map<Long, OrderAggregate> orderAggMap = ModelUtils.toMap(orderAggList, OrderAggregate::getId);
        List<OrderDeleteCmdResult> results = Lists.newArrayListWithExpectedSize(orderAggList.size());
        for (RevisionRecord item : records) {
            OrderDeleteCmdResult result = new OrderDeleteCmdResult();

            OrderAggregate orderAgg = orderAggMap.get(item.getId());
            orderAgg.setRevision(item.getRevision());
            result.setOrder(orderAgg);
            result.setOrderItems(orderAgg.getOrderItems());
            results.add(result);
        }
        return results;
    }
}
