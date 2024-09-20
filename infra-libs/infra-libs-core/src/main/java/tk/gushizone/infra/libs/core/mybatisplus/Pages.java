package tk.gushizone.infra.libs.core.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.constant.Orders;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;
import tk.gushizone.infra.libs.base.entity.query.PagedData;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingData;

import java.util.List;

/**
 * 类型转换: mybatis-plus 的分页 <-> 系统的分页
 *
 * @author gushizone
 * @since 2024/9/10
 */
public class Pages {

    /**
     * 转变为结果
     */
    public static <T> PagedResult<T> toResult(Page<?> page, List<T> records) {
        if (CollectionUtils.isEmpty(records)) {
            records = Lists.newArrayList();
        }
        PagedResult<T> pagedResult = new PagedResult<>();
        pagedResult.setRecords(records);

        PagedData pagedData = new PagedData();
        pagedData.setCurrent(page.getCurrent());
        pagedData.setSize(page.getSize());
        if (page.getSize() >= 0) {
            pagedData.setTotal(page.getTotal());
        } else {
            // 避免未分页时, 计数不对
            pagedData.setTotal(records.size());
        }
        pagedData.setOrders(toEntry(page.orders()));

        pagedResult.setPage(pagedData);
        return pagedResult;
    }

    /**
     * 转变为结果
     */
    @SuppressWarnings("unchecked")
    public static <T> PagedResult<T> toResult(Page<?> page) {
        return (PagedResult<T>) toResult(page, page.getRecords());
    }


    /**
     * 转变为 mybatis-plus 参数
     */
    public static <T> Page<T> toPage(PagingData PagingData) {
        Page<T> result = Page.of(PagingData.getCurrent(), PagingData.getSize());
        result.setOrders(toOrder(PagingData.getOrders()));
        return result;
    }

    /**
     * 转换排序集
     */
    private static List<OrderItem> toOrder(List<OrderEntry> entries) {
        if (CollectionUtils.isEmpty(entries)) {
            return Lists.newArrayList();
        }
        List<OrderItem> results = Lists.newArrayListWithExpectedSize(entries.size());
        for (OrderEntry order : entries) {
            OrderItem result = new OrderItem();
            result.setColumn(order.getColumn());
            result.setAsc(Orders.ASC.equals(order.getOrder()));
            results.add(result);
        }
        return results;
    }

    /**
     * 转换排序集
     */
    private static List<OrderEntry> toEntry(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Lists.newArrayList();
        }
        List<OrderEntry> results = Lists.newArrayListWithExpectedSize(items.size());
        for (OrderItem item : items) {
            OrderEntry result = new OrderEntry();
            result.setColumn(item.getColumn());
            result.setOrder(item.isAsc() ? Orders.ASC : Orders.DESC);
            results.add(result);
        }
        return results;
    }
}
