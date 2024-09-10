package tk.gushizone.infra.libs.base.query;

import lombok.Data;

@Data
public class PagingParam<T> {

    private Paging page;

    private T param;

    public PagingParam(Paging page, T param) {
        this.param = param;
        this.page = page;
    }
}
