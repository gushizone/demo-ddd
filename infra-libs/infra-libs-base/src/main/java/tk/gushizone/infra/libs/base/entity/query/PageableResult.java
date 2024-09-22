package tk.gushizone.infra.libs.base.entity.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 此结果集仅用于数据传输, 不会对rest暴露 todo
 *
 * @author gushizone
 * @since 2024/9/10
 */
@Getter
@Setter
public class PageableResult<T> {

    private PagedData page;

    private List<T> records;
}
