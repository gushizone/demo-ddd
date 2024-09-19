package tk.gushizone.infra.libs.base.entity.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 已分页
 *
 * @author gushizone
 * @since 2023/6/1
 */
@Getter
@Setter
public class PagedData {

    /***
     * 页码
     */
    private long current = 1;
    /***
     * 页容量
     */
    private long size = 10;
    /***
     * 总数
     */
    private long total = 0;
    /**
     * 排序集
     */
    private List<OrderEntry> orders;
}