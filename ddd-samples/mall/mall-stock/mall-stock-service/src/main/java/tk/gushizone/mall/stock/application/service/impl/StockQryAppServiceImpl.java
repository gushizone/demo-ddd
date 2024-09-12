package tk.gushizone.mall.stock.application.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.stock.application.assembler.StockAppAssembler;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.service.StockDomainService;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;

@Component
public class StockQryAppServiceImpl implements StockQryAppService {

    @Resource
    private StockDomainService stockDomainService;

    @Override
    public RestResponse<List<StockApiRsp>> query(StockQryApiReq req) {
        StockQry stockQry = StockAppAssembler.toQry(req);

        // todo 和分页查询复用
        List<StockAggregate> stocks = stockDomainService.query(stockQry);

        return RestResponse.ok(StockAppAssembler.toApiRsp(stocks));
    }
}
