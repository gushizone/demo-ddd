package tk.gushizone.mall.stock.application.service;

import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.mall.stock.api.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.api.dto.rsp.StockApiRsp;

public interface StockQryAppService {

    SearchRestResponse<StockApiRsp> queryByApi(SearchRestRequest<StockQryApiReq> req);

}
