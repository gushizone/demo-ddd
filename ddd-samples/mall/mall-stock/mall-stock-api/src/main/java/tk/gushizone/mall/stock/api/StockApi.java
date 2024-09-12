package tk.gushizone.mall.stock.api;

import org.springframework.web.bind.annotation.*;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

public interface StockApi {

    @PostMapping("/api/stocks/list")
    RestResponse<List<StockApiRsp>> query(@RequestBody StockQryApiReq req);

}
