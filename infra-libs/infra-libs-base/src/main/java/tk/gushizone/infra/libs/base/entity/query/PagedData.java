package tk.gushizone.infra.libs.base.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Setter
@Getter
@Schema(description = "已分页")
public class PagedData {

    /***
     * 页码
     */
    @Schema(description = "页码", defaultValue = "1")
    private long current = 1;
    /***
     * 页容量
     */
    @Schema(description = "页容量", defaultValue = "10")
    private long size = 10;
    /***
     * 总数
     */
    @Schema(description = "总数", defaultValue = "0")
    private long total = 0;
    /**
     * 排序集
     */
    @Schema(description = "排序集")
    private List<OrderEntry> orders;
}