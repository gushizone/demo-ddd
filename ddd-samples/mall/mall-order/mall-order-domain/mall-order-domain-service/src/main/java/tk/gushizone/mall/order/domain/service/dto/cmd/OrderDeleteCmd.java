package tk.gushizone.mall.order.domain.service.dto.cmd;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.gushizone.infra.libs.base.entity.CommandRecord;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;

import java.util.List;

/**
 * todo 通用抽取
 *
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
@Accessors(chain = true)
public class OrderDeleteCmd {

    private List<CommandRecord> records;

    private List<OrderAggregate> orderAggregates;
}
