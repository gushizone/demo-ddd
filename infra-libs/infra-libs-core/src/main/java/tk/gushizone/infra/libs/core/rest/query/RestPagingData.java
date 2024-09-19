package tk.gushizone.infra.libs.core.rest.query;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import tk.gushizone.infra.libs.base.entity.query.PagedData;
import tk.gushizone.infra.libs.base.entity.query.PagingData;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Getter
@Setter
@Schema(description = "分页")
public class RestPagingData {

    @Schema(description = "页码", defaultValue = "1")
    private long current = 1;

    @Schema(description = "页容量", defaultValue = "10")
    private long size = 10;

    @Valid
    @Schema(description = "排序集")
    private List<RestOrderEntry> orders = Lists.newArrayList();

    public static RestPagingData noPaging() {
        RestPagingData result = new RestPagingData();
        result.setCurrent(1);
        result.setSize(-1);
        return result;
    }

    public static RestPagingData of(PagedData page) {
        RestPagingData pagedData = new RestPagingData();
        pagedData.setCurrent(page.getCurrent());
        pagedData.setSize(page.getSize());
        pagedData.setOrders(RestOrderEntry.of(page.getOrders()));
        return pagedData;
    }

    public static PagingData off(RestPagingData page) {
        PagingData result = new PagingData();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setOrders(RestOrderEntry.off(page.getOrders()));
        return result;
    }
    
}