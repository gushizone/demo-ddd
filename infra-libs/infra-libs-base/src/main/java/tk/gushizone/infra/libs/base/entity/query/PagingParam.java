package tk.gushizone.infra.libs.base.entity.query;

public interface PagingParam<T> {

    PagingData getPage();

    void setPage(PagingData page);

    T getParam();

    void setParam(T param);
}
