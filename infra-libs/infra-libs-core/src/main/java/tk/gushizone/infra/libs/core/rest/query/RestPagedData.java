package tk.gushizone.infra.libs.core.rest.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import tk.gushizone.infra.libs.base.entity.query.PagedData;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Setter
@Getter
@Schema(description = "已分页")
public class RestPagedData {

    /***
     * 当前页
     */
    @Schema(description = "页码", defaultValue = "1")
    private long current = 1;
    /***
     * 每页数量, 默认为10
     */
    @Schema(description = "页容量", defaultValue = "10")
    private long size = 10;
    /***
     * 总数
     */
    @Schema(description = "总数", defaultValue = "0")
    private long total = 0;
    /**
     * 排序项
     */
    @Schema(description = "排序集")
    private List<RestOrderEntry> orders;


    public static RestPagedData of(PagedData page) {
        RestPagedData pagedData = new RestPagedData();
        pagedData.setCurrent(page.getCurrent());
        pagedData.setSize(page.getSize());
        pagedData.setTotal(page.getTotal());
        pagedData.setOrders(RestOrderEntry.of(page.getOrders()));
        return pagedData;
    }

    public static PagedData to(RestPagedData page) {
        PagedData result = new PagedData();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setOrders(RestOrderEntry.off(page.getOrders()));
        return result;
    }
}