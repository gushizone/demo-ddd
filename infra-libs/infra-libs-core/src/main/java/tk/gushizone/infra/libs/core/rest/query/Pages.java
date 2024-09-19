package tk.gushizone.infra.libs.core.rest.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.constant.Orders;
import tk.gushizone.infra.libs.base.entity.query.OrderEntry;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.base.entity.query.PagingData;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;

import java.util.List;

/**
 * 类型转换
 * - 使用接口解耦, 不暴露实现类
 *
 * @author gushizone
 * @since 2024/9/10
 */
public class Pages {

    /**
     * 转变为参数
     */
    public static <T> PagingParam<T> toParam(RestPagingData restPaging, T param) {
        return new RestPagingParam<>(restPaging, param);
    }

    /**
     * 转变为结果
     */
    public static <T> PagedResult<T> toResult(Page<?> page, List<T> records) {
        if (CollectionUtils.isEmpty(records)) {
            records = Lists.newArrayList();
        }
        TransmittablePagedResult<T> transmittablePagedResult = new TransmittablePagedResult<>();
        transmittablePagedResult.setRecords(records);

        RestPagedData restPaged = new RestPagedData();
        restPaged.setCurrent(page.getCurrent());
        restPaged.setSize(page.getSize());
        if (page.getSize() >= 0) {
            restPaged.setTotal(page.getTotal());
        } else {
            // 避免未分页时, 计数不对
            restPaged.setTotal(records.size());
        }
        restPaged.setOrders(toEntry(page.orders()));

        transmittablePagedResult.setPage(restPaged);
        return transmittablePagedResult;
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
    public static <T> Page<T> toPage(PagingData pagingData) {
        Page<T> result = Page.of(pagingData.getCurrent(), pagingData.getSize());
        result.setOrders(toOrder(pagingData.getOrders()));
        return result;
    }

    /**
     * 转换排序集 - 请求
     */
    private static List<OrderItem> toOrder(List<? extends OrderEntry> entries) {
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
     * 转换排序集 - 响应
     */
    private static List<RestOrderEntry> toEntry(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Lists.newArrayList();
        }
        List<RestOrderEntry> results = Lists.newArrayListWithExpectedSize(items.size());
        for (OrderItem item : items) {
            RestOrderEntry result = new RestOrderEntry();
            result.setColumn(item.getColumn());
            result.setOrder(item.isAsc() ? Orders.ASC : Orders.DESC);
            results.add(result);
        }
        return results;
    }
}
