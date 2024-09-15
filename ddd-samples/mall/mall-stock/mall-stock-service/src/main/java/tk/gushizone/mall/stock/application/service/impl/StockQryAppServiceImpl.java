package tk.gushizone.mall.stock.application.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.infra.libs.core.rest.query.Pages;
import tk.gushizone.mall.stock.application.assembler.StockAppAssembler;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.service.StockDomainService;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

@Component
public class StockQryAppServiceImpl implements StockQryAppService {

    @Resource
    private StockDomainService stockDomainService;

    @Override
    public SearchRestResponse<StockApiRsp> queryByApi(SearchRestRequest<StockQryApiReq> req) {
        StockQry stockQry = StockAppAssembler.toQry(req.getParam());

        PagedResult<StockAggregate> stackPagedResult = stockDomainService.query(Pages.toParam(req.getPage(), stockQry));

        return SearchRestResponse.ok(stackPagedResult.getPage(), StockAppAssembler.toApiRsp(stackPagedResult.getRecords()));
    }
}
