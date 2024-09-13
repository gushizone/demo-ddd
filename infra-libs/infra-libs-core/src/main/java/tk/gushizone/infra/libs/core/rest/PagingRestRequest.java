package tk.gushizone.infra.libs.core.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import tk.gushizone.infra.libs.core.rest.query.RestPagingData;
import tk.gushizone.infra.libs.core.rest.query.RestPagingParam;
import tk.gushizone.infra.libs.core.util.Pages;

/**
 * todo 请求场景
 *
 * @author gushizone
 * @since 2024/9/10
 */
@Schema(description = "分页请求")
public class PagingRestRequest<T> extends RestPagingParam<T> {


    public static <T> PagingRestRequest<T> of(T param) {
        PagingRestRequest<T> result = new PagingRestRequest<>();
        result.setPage(RestPagingData.noPaging());
        result.setParam(param);
        return result;
    }
}
