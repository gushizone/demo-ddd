package tk.gushizone.mall.order.application.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.infra.libs.core.util.Pages;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.application.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.application.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.service.OrderQueryAppService;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.service.OrderDomainService;

import jakarta.annotation.Resource;
import java.util.List;

@Service
public class OrderQueryAppServiceImpl implements OrderQueryAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Override
    public PagedRestResponse<OrderRsp> query(PagingRestRequest<OrderQryReq> restPagingParam) {

        OrderQry orderQry = OrderAppAssembler.toQry(restPagingParam.getParam());

        PagedResult<OrderAggregate> orderPagedResult = orderDomainService.query(Pages.toParam(restPagingParam.getPage(), orderQry));

        List<OrderRsp> orderRspList = OrderAppAssembler.toRsp(orderPagedResult.getRecords());

        return PagedRestResponse.ok(orderPagedResult.getPage(), orderRspList);
    }
}
