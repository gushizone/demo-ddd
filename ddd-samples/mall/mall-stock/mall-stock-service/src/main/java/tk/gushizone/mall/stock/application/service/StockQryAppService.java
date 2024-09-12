package tk.gushizone.mall.stock.application.service;

import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

public interface StockQryAppService {

    PagedRestResponse<StockApiRsp> queryByApi(PagingRestRequest<StockQryApiReq> req);

}
