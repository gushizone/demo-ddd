package tk.gushizone.infra.libs.core.rest;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.entity.query.PagedData;

import java.io.Serializable;
import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SearchRestResponse", description = "搜索响应")
public class SearchRestResponse<T> extends RestResponse<List<T>> implements Serializable {

    @Schema(description = "分页")
    private PagedData page;

    public static <T> SearchRestResponse<T> ok(PagedData page, List<T> list) {
        SearchRestResponse<T> result = new SearchRestResponse<>();
        result.setPage(page);
        result.setCode(Status.OK.code());
        result.setMsg(Status.OK.label());
        result.setData(list);
        return result;
    }

    public T findFirst() {
        if (CollectionUtils.isEmpty(this.getData())) {
            return null;
        }
        return this.getData().get(0);
    }
}
