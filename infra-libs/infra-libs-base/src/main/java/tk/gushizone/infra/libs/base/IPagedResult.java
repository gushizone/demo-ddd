package tk.gushizone.infra.libs.base;

import tk.gushizone.infra.libs.base.query.Paged;

import java.util.List;

public interface IPagedResult<T> {

    Paged getPage();

    void setPage(Paged page);

    List<T> getRecords();

    void setRecords(List<T> records);
}
