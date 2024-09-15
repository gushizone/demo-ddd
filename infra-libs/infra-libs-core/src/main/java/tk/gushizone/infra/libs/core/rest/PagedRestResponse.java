package tk.gushizone.infra.libs.core.rest;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.query.PagedData;
import tk.gushizone.infra.libs.core.rest.query.RestPagedData;

import java.io.Serializable;
import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分页响应")
public class PagedRestResponse<T> extends RestResponse<List<T>> implements Serializable {

    @Schema(description = "已分页")
    private RestPagedData page;

    public static <T> PagedRestResponse<T> ok(PagedData page, List<T> list) {
        PagedRestResponse<T> result = new PagedRestResponse<>();
        result.setPage((RestPagedData) page);
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
