package tk.gushizone.infra.libs.base.query;

public interface PagingParam<T> {

    PagingData getPage();

    void setPage(PagingData page);

    T getParam();

    void setParam(T param);
}
