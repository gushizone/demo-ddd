package tk.gushizone.infra.libs.core.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.Paged;
import tk.gushizone.infra.libs.base.query.Paging;
import tk.gushizone.infra.libs.base.query.PagingParam;

import java.util.List;

/**
 * todo 抽取
 * todo 排序
 */
public class Pages {

    public static <T> PagedResult<T> toResult(Paged paged, List<T> records) {
        PagedResult<T> pagedResult = new PagedResult<>();
        pagedResult.setRecords(records);

        // todo
        pagedResult.setPage(paged);
        return pagedResult;
    }

    // todo 解耦 接口
    public static <T> PagedResult<T> toResult(Page<?> page, List<T> records) {
        PagedResult<T> pagedResult = new PagedResult<>();
        pagedResult.setRecords(records);

        Paged paged = new Paged();
        paged.setCurrent(page.getCurrent());
        paged.setSize(page.getSize());
        paged.setTotal(page.getTotal());
//        pagination.setOrders(page.orders()); todo

        pagedResult.setPage(paged);
        return pagedResult;
    }

    public static <T> PagedResult<T> toResult(Page<?> page) {
        return toResult(page, Lists.newArrayList());
    }

    public static <T> PagingParam<T> toParam(Paging paging, T param) {
        return new PagingParam<>(paging, param);
    }

    public static <T> Page<T> toPage(Paging paging) {
        return Page.of(paging.getCurrent(), paging.getSize());
    }
}
