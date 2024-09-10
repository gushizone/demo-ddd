package tk.gushizone.mall.stock.application.service.impl;

import org.springframework.stereotype.Component;
import tk.gushizone.mall.stock.application.assembler.StockAppAssembler;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.service.StockDomainService;
import tk.gushizone.mall.stock.dto.req.qry.StockQryReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StockQryAppServiceImpl implements StockQryAppService {

    @Resource
    private StockDomainService stockDomainService;

    @Override
    public List<StockApiRsp> query(StockQryReq req) {
        StockQry stockQry = StockAppAssembler.toQry(req);

        List<StockAggregate> stocks = stockDomainService.query(stockQry);

        return StockAppAssembler.toApi(stocks);
    }
}
