package tk.gushizone.infra.libs.core.rest.query;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import tk.gushizone.infra.libs.base.query.PagingData;
import tk.gushizone.infra.libs.base.query.PagingParam;

import javax.validation.Valid;

/**
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询参数")
public class RestPagingParam<T> implements PagingParam<T> {

    @Valid
    @Schema(description = "分页参数")
    private RestPagingData page;

//    @Valid
    @Setter
    @Schema(description = "查询参数")
    private T param;

    @Override
    @JsonDeserialize(as = RestPagingData.class)
    public void setPage(PagingData page) {
        this.page = (RestPagingData) page;
    }
}
