package tk.gushizone.mall.stock.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

public interface StockApi {

    @PostMapping("/api/stocks/search")
    SearchRestResponse<StockApiRsp> query(@RequestBody SearchRestRequest<StockQryApiReq> req);

}
