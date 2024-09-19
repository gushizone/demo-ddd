package tk.gushizone.infra.libs.core.rest.query;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.constant.Orders;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;
import tk.gushizone.infra.libs.core.validation.constraints.Options;

import java.util.List;

/**
 * @author gushizone
 * @since 2024/9/19
 */
@Getter
@Setter
@Schema(description = "排序项")
public class RestOrderEntry {

    @Schema(description = "排序列", example = "id")
    private String column;

    @Schema(description = "排序", example = "desc", allowableValues = {Orders.ASC, Orders.DESC})
    @Options(options = {Orders.ASC, Orders.DESC})
    private String order;

    public static List<RestOrderEntry> of(List<OrderEntry> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return Lists.newArrayList();
        }
        List<RestOrderEntry> results = Lists.newArrayList();
        for (OrderEntry item : orders) {
            results.add(RestOrderEntry.of(item));
        }
        return results;
    }

    private static RestOrderEntry of(OrderEntry item) {
        RestOrderEntry result = new RestOrderEntry();
        result.setColumn(item.getColumn());
        result.setOrder(item.getOrder());
        return result;
    }

    public static List<OrderEntry> off(List<RestOrderEntry> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<OrderEntry> results = Lists.newArrayList();
        for (RestOrderEntry item : list) {
            results.add(RestOrderEntry.off(item));
        }
        return results;
    }

    private static OrderEntry off(RestOrderEntry item) {
        OrderEntry result = new OrderEntry();
        result.setColumn(item.getColumn());
        result.setOrder(item.getOrder());
        return result;
    }
}
