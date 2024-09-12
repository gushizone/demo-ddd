package tk.gushizone.mall.stock.application.service;

import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

public interface StockQryAppService {

    RestResponse<List<StockApiRsp>> query(StockQryApiReq req);

}
