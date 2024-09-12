package tk.gushizone.mall.stock.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

public interface StockApi {

    @PostMapping("/api/stocks/search")
    PagedRestResponse<StockApiRsp> query(@RequestBody PagingRestRequest<StockQryApiReq> req);

}
