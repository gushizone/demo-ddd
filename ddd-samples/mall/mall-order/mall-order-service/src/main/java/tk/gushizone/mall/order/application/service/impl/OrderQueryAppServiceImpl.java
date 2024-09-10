package tk.gushizone.mall.order.application.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.infra.libs.core.util.Pages;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.application.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.application.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.service.OrderQueryAppService;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.domain.service.OrderDomainService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderQueryAppServiceImpl implements OrderQueryAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Override
    public PagedResult<OrderRsp> query(PagingParam<OrderQryReq> pagingParam) {

        OrderQry orderQry = OrderAppAssembler.toQry(pagingParam.getParam());

        PagedResult<OrderAggregate> orderPagedResult = orderDomainService.query(Pages.toParam(pagingParam.getPage(), orderQry));

        List<OrderRsp> orderRspList = OrderAppAssembler.toRsp(orderPagedResult.getRecords());

        return Pages.toResult(orderPagedResult.getPage(), orderRspList);
    }
}
