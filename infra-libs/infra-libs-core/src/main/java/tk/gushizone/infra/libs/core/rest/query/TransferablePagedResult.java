package tk.gushizone.infra.libs.core.rest.query;

import lombok.*;
import tk.gushizone.infra.libs.base.query.PagedData;
import tk.gushizone.infra.libs.base.query.PagedResult;

import java.util.List;

/**
 * 此结果集仅用于数据传输, 不会对rest暴露
 *
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class TransferablePagedResult<T> implements PagedResult<T> {

    private RestPagedData page;

    @Setter
    private List<T> records;

    @Override
    public void setPage(PagedData page) {
        this.page = (RestPagedData) page;
    }
}