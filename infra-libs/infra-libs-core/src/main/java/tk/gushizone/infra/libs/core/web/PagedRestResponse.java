package tk.gushizone.infra.libs.core.web;


import lombok.Data;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.Paged;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gushizone
 * @since 2023/6/1
 */
@Data
public class PagedRestResponse<T> extends RestResponse<List<T>> implements Serializable {

    private Paged page;

    public static <T> PagedRestResponse<T> ok(PagedResult<T> pagedResult) {
        PagedRestResponse<T> pagedRestResponse = new PagedRestResponse<>();
        pagedRestResponse.setCode(Status.OK.code());
        pagedRestResponse.setMsg(Status.OK.label());
        pagedRestResponse.setData(pagedResult.getRecords());
        pagedRestResponse.setPage(pagedResult.getPage());
        return pagedRestResponse;
    }

}
