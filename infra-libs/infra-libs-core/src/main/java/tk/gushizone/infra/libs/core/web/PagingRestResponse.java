package tk.gushizone.infra.libs.core.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author gushizone
 * @since 2023/6/1
 */
@Data
public class PagingRestResponse<T> extends RestResponse<T> implements Serializable {

    private Pagination page;

    public PagingRestResponse(T data, Pagination page) {
        super(Status.OK.code(), Status.OK.label(), data);
        this.page = page;
    }

    @SuppressWarnings("unchecked")
    public static <T> PagingRestResponse<T> ok(IPage<?> iPage) {

        Pagination pagination = new Pagination();
        pagination.setCurrent(iPage.getCurrent());
        pagination.setSize(iPage.getSize());
        pagination.setTotal(iPage.getTotal());

        // todo 排序

        return new PagingRestResponse<>((T)iPage.getRecords(), pagination);
    }

}
