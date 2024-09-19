package tk.gushizone.infra.libs.base.entity.query;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页
 *
 * @author gushizone
 * @since 2023/6/1
 */
@Getter
@Setter
public class PagingData {

    /**
     * 页码
     */
    private long current = 1;

    /**
     * 页容量
     */
    private long size = 10;

    /**
     * 排序集
     */
    private List<OrderEntry> orders = Lists.newArrayList();


    public static PagingData noPaging() {
        PagingData result = new PagingData();
        result.setCurrent(1);
        result.setSize(-1);
        return result;
    }
}