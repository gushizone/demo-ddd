package tk.gushizone.mall.stock.application.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.mall.stock.application.assembler.StockAppAssembler;
import tk.gushizone.mall.stock.application.service.StockQryAppService;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.service.StockDomainService;
import tk.gushizone.mall.stock.api.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.api.dto.rsp.StockApiRsp;

@Component
public class StockQryAppServiceImpl implements StockQryAppService {

    @Resource
    private StockDomainService stockDomainService;

    @Override
    public SearchRestResponse<StockApiRsp> queryByApi(SearchRestRequest<StockQryApiReq> req) {
        StockQry stockQry = StockAppAssembler.toQry(req.getParam());

        PageableResult<StockAggregate> stackPageableResult = stockDomainService.query(PageableParam.of(req.getPage(), stockQry));

        return SearchRestResponse.ok(stackPageableResult.getPage(), StockAppAssembler.toApiRsp(stackPageableResult.getRecords()));
    }
}
