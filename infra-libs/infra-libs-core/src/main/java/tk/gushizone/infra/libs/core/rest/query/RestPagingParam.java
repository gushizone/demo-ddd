package tk.gushizone.infra.libs.core.rest.query;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.*;
import tk.gushizone.infra.libs.base.entity.query.PagingData;
import tk.gushizone.infra.libs.base.entity.query.PagingParam;

/**
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RestPagingParam", description = "分页查询参数")
public class RestPagingParam<T> implements PagingParam<T> {

    @Valid
    @Schema(description = "分页参数")
    private RestPagingData page = RestPagingData.noPaging();

    @Valid
    @Setter
    @Schema(description = "查询参数")
    private T param;

    @Override
    @JsonDeserialize(as = RestPagingData.class)
    public void setPage(PagingData page) {
        this.page = (RestPagingData) page;
    }
}
