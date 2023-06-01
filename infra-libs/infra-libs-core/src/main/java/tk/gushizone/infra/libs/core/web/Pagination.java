package tk.gushizone.infra.libs.core.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Data
public class Pagination implements Serializable {

    /**
     * 是否分页
     */
    private boolean enabled = true;
    /***
     * 当前页
     */
    private long current = 1;
    /***
     * 每页数量, 默认为10
     */
    private long size = 10;
    /***
     * 总数
     */
    private long total = 0;

    /**
     * 排序
     * field => id
     * field:sort => id:asc
     * field:sort1,field:sort2 => createBy:asc,createAt:desc
     */
    private String orderBy = "";

    public Pagination() {
    }


    public Pagination(long current, long size) {
        this(current, size, 0);
    }

    public Pagination(long current, long size, long total) {
        this(true, current, size, total);
    }

    public Pagination(boolean enabled, long current, long size, long total) {
        this.enabled = enabled;
        this.current = current;
        this.size = size;
        this.total = total;
    }

    /**
     * 不分页构造
     */
    public static Pagination noPagination(long total) {
        return new Pagination(false, 1, total, total);
    }
}