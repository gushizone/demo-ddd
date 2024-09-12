package tk.gushizone.mall.stock.adapter.in.api;

import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.mall.stock.api.StockApi;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import jakarta.annotation.Resource;

@RestController
public class StockApiImpl implements StockApi {

    @Resource
    private StockQryAppService stockQryAppService;

    @Override
    public PagedRestResponse<StockApiRsp> query(PagingRestRequest<StockQryApiReq> req) {
        return stockQryAppService.queryByApi(req);
    }
}
