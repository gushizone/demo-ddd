package tk.gushizone.mall.stock.application.service;

import tk.gushizone.mall.stock.dto.req.qry.StockQryReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

public interface StockQryAppService {

    List<StockApiRsp> query(StockQryReq req);

}
