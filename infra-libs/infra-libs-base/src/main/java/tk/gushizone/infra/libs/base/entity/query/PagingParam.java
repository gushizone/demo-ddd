package tk.gushizone.infra.libs.base.entity.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询参数
 *
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@Setter
public class PagingParam<T> {

    /**
     * 分页参数
     */
    private PagingData page = PagingData.noPaging();

    /**
     * 查询参数
     */
    private T param;

    public static <T> PagingParam<T> of(PagingData page, T param) {
        PagingParam<T> pagingParam = new PagingParam<>();
        pagingParam.setPage(page);
        pagingParam.setParam(param);
        return pagingParam;
    }

    public static <T> PagingParam<T> of(T param) {
        return of(PagingData.noPaging(), param);
    }
}
