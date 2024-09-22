package tk.gushizone.mall.stock.adapter.in.api;

import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.mall.stock.api.StockApi;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.api.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.api.dto.rsp.StockApiRsp;

import jakarta.annotation.Resource;

@RestController
public class StockApiImpl implements StockApi {

    @Resource
    private StockQryAppService stockQryAppService;

    @Override
    public SearchRestResponse<StockApiRsp> query(SearchRestRequest<StockQryApiReq> req) {
        return stockQryAppService.queryByApi(req);
    }
}
