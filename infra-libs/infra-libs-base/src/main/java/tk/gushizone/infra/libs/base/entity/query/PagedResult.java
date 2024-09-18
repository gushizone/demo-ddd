package tk.gushizone.infra.libs.base.entity.query;

import java.util.List;

public interface PagedResult<T> {

    PagedData getPage();

    void setPage(PagedData page);

    List<T> getRecords();

    void setRecords(List<T> records);
}
