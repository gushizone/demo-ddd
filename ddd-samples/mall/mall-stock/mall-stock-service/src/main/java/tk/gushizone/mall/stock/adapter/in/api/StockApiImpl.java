package tk.gushizone.mall.stock.adapter.in.api;

import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.mall.stock.api.StockApi;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.dto.req.qry.StockQryReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StockApiImpl implements StockApi {

    @Resource
    private StockQryAppService stockQryAppService;

    @Override
    public List<StockApiRsp> query(StockQryReq req) {
        return stockQryAppService.query(req);
    }
}
