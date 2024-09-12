package tk.gushizone.infra.libs.core.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.query.OrderEntry;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.PagingData;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.infra.libs.core.rest.query.*;

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
        TransferablePagedResult<T> transferablePagedResult = new TransferablePagedResult<>();
        transferablePagedResult.setRecords(records);

        RestPagedData restPaged = new RestPagedData();
        restPaged.setCurrent(page.getCurrent());
        restPaged.setSize(page.getSize());
        restPaged.setTotal(page.getTotal());
        restPaged.setOrders(toEntry(page.orders()));

        transferablePagedResult.setPage(restPaged);
        return transferablePagedResult;
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
            result.setAsc(RestOrderEntry.ORDER_AES.equals(order.getOrder()));
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
            result.setOrder(item.isAsc() ? RestOrderEntry.ORDER_AES : RestOrderEntry.ORDER_DESC);
            results.add(result);
        }
        return results;
    }

    /**
     * 不分页
     * - todo 是否需要
     * - todo 默认排序问题
     */
    private static RestPagingData noPaging() {
        RestPagingData restPagingData = new RestPagingData();
        restPagingData.setCurrent(1);
        restPagingData.setSize(-1);
        restPagingData.setOrders(Lists.newArrayList());
        return restPagingData;
    }

    public static <T> PagingRestRequest<T> toReq(T stockQryReq) {
        PagingRestRequest<T> result = new PagingRestRequest<>();
        result.setPage(noPaging());
        result.setParam(stockQryReq);
        return result;
    }
}
