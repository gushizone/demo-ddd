package tk.gushizone.infra.libs.core.rest;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.gushizone.infra.libs.base.entity.query.PagingData;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;

import java.util.List;

/**
 * 搜索请求
 *
 * @author gushizone
 * @since 2024/9/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SearchRestRequest", description = "搜索请求")
public class SearchRestRequest<T> extends PagingParam<T> {

    @Schema(title = "用例", description = "应用场景")
    private String useCase;

    @Schema(title = "包含列", description = "可供导出使用")
    private List<String> columns = Lists.newArrayList();

    public static <T> SearchRestRequest<T> of(T param) {
        SearchRestRequest<T> result = new SearchRestRequest<>();
        result.setPage(PagingData.noPaging());
        result.setParam(param);
        return result;
    }
}
