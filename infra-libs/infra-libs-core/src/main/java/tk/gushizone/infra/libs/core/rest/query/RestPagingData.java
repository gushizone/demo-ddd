package tk.gushizone.infra.libs.core.rest.query;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.*;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;
import tk.gushizone.infra.libs.base.entity.query.PagingData;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Schema(description = "分页")
public class RestPagingData implements PagingData {

    @Getter
    @Setter
    @Schema(description = "页码", defaultValue = "1")
    private long current = 1;

    @Getter
    @Setter
    @Schema(description = "页容量", defaultValue = "10")
    private long size = 10;

    @Valid
    @Schema(description = "排序集")
    private List<RestOrderEntry> orders = Lists.newArrayList();


    @Override
    @SuppressWarnings("unchecked")
    @JsonDeserialize(contentAs = RestOrderEntry.class)
    public void setOrders(List<? extends OrderEntry> orders) {
        this.orders = (List<RestOrderEntry>) orders;
    }

    public static RestPagingData noPaging() {
        RestPagingData result = new RestPagingData();
        result.setCurrent(1);
        result.setSize(-1);
        return result;
    }
}