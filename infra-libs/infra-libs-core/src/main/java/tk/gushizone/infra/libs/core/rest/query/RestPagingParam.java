package tk.gushizone.infra.libs.core.rest.query;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import tk.gushizone.infra.libs.base.query.PagingData;
import tk.gushizone.infra.libs.base.query.PagingParam;

/**
 * @author zhangwei
 * @since 2024/9/10
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询参数")
public class RestPagingParam<T> implements PagingParam<T> {

    @Schema(description = "分页参数")
    private RestPagingData page;

    @Setter
    @Schema(description = "查询参数")
    private T param;

    @Override
    @JsonDeserialize(as = RestPagingData.class)
    public void setPage(PagingData page) {
        this.page = (RestPagingData) page;
    }
}
