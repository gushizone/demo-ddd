package tk.gushizone.mall.order.application.service.impl;

import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.query.PagedResult;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.core.rest.query.Pages;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.service.OrderQueryAppService;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.service.OrderDomainService;

import java.util.List;

@Service
public class OrderQueryAppServiceImpl implements OrderQueryAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Override
    public SearchRestResponse<OrderRsp> query(SearchRestRequest<OrderQryReq> req) {

        OrderQry orderQry = OrderAppAssembler.toQry(req.getParam());

        PagedResult<OrderAggregate> orderPagedResult = orderDomainService.query(Pages.toParam(req.getPage(), orderQry));

        // todo 商品查询

        List<OrderRsp> orderRspList = OrderAppAssembler.toRsp(orderPagedResult.getRecords());
        return SearchRestResponse.ok(orderPagedResult.getPage(), orderRspList);
    }

    /**
     * 简单的明细查询可以直接复用list查询
     */
    @Override
    public RestResponse<OrderRsp> query(Long id) {

        SearchRestRequest<OrderQryReq> pagingReq = SearchRestRequest.of(new OrderQryReq()
                .setIds(Lists.newArrayList(id)));
        OrderRsp orderRsp = this.query(pagingReq).findFirst();
        return RestResponse.ok(orderRsp);
    }
}
