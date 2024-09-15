package tk.gushizone.infra.libs.core.rest;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.gushizone.infra.libs.core.rest.query.RestPagingData;
import tk.gushizone.infra.libs.core.rest.query.RestPagingParam;

import java.util.List;

/**
 * todo 请求场景
 *
 * @author gushizone
 * @since 2024/9/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分页请求")
public class PagingRestRequest<T> extends RestPagingParam<T> {

    @Schema(title = "用例", description = "应用场景")
    private String useCase;

    @Schema(title = "包含列", description = "可供导出使用")
    private List<String> columns = Lists.newArrayList();

    public static <T> PagingRestRequest<T> of(T param) {
        PagingRestRequest<T> result = new PagingRestRequest<>();
        result.setPage(RestPagingData.noPaging());
        result.setParam(param);
        return result;
    }
}
