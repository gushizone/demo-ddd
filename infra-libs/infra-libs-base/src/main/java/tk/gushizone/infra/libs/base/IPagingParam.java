package tk.gushizone.infra.libs.base;

public interface IPagingParam<T> {

    IPaging getPage();

    void setPage(IPaging page);

    T getParam();

    void setParam(T param);
}
