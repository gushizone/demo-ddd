package tk.gushizone.infra.libs.core.rest.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;
import tk.gushizone.infra.libs.base.entity.query.PagedData;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Schema(description = "已分页")
public class RestPagedData implements PagedData {

    /***
     * 当前页
     */
    @Setter
    @Schema(description = "页码", defaultValue = "1")
    private long current = 1;
    /***
     * 每页数量, 默认为10
     */
    @Setter
    @Schema(description = "页容量", defaultValue = "10")
    private long size = 10;
    /***
     * 总数
     */
    @Setter
    @Schema(description = "总数", defaultValue = "0")
    private long total = 0;
    /**
     * 排序项
     */
    @Schema(description = "排序集")
    private List<RestOrderEntry> orders;


    @Override
    @SuppressWarnings("unchecked")
    public void setOrders(List<? extends OrderEntry> orders) {
        this.orders = (List<RestOrderEntry>) orders;
    }
}