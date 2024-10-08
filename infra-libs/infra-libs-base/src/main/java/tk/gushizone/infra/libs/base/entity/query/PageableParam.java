package tk.gushizone.infra.libs.base.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@Setter
@Schema(description = "分页查询参数")
public class PageableParam<T> {

    @Valid
    @Schema(description = "分页参数")
    private PagingData page = PagingData.noPaging();

    @Valid
    @Setter
    @Schema(description = "查询参数")
    private T param;

    public static <T> PageableParam<T> of(PagingData page, T param) {
        PageableParam<T> pageableParam = new PageableParam<>();
        pageableParam.setPage(page);
        pageableParam.setParam(param);
        return pageableParam;
    }

    public static <T> PageableParam<T> of(T param) {
        return of(PagingData.noPaging(), param);
    }
}
