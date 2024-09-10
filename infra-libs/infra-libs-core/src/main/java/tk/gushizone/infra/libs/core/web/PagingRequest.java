package tk.gushizone.infra.libs.core.web;

import io.swagger.v3.oas.annotations.media.Schema;
import tk.gushizone.infra.libs.base.query.Paging;
import tk.gushizone.infra.libs.base.query.PagingParam;

//@Data
@Schema(description = "分页请求")
public class PagingRequest<T> extends PagingParam<T> {

    public PagingRequest(Paging page, T param) {
        super(page, param);
    }

    @Override
    @Schema(description = "分页参数")
    public Paging getPage() {
        return super.getPage();
    }

    @Override
    @Schema(description = "查询参数")
    public T getParam() {
        return super.getParam();
    }
}
