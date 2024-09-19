package tk.gushizone.infra.libs.core.rest.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;

/**
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@Setter
@Schema(name = "RestPagingParam", description = "分页查询参数")
public class RestPagingParam<T> {

    @Valid
    @Schema(description = "分页参数")
    private RestPagingData page = RestPagingData.noPaging();

    @Valid
    @Setter
    @Schema(description = "查询参数")
    private T param;

    public static <T> PagingParam<T> off(RestPagingData page, T param) {
        return PagingParam.of(RestPagingData.off(page), param);
    }

    public <R> PagingParam<R> off(R param) {
        return PagingParam.of(RestPagingData.off(page), param);
    }
}
