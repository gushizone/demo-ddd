package tk.gushizone.mall.stock.api;

import org.springframework.web.bind.annotation.*;
import tk.gushizone.mall.stock.dto.req.qry.StockQryReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

public interface StockApi {

    @PostMapping("/api/stocks/list")
    List<StockApiRsp> query(@RequestBody StockQryReq req);

}
