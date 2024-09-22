package tk.gushizone.mall.order.application.service.impl;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.excel.exp.OrderExp;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.application.service.OrderQryAppService;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.service.dto.qry.OrderQry;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.order.infrastructure.util.ExcelUtils;

import java.util.List;

@Service
public class OrderQryAppServiceImpl implements OrderQryAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Override
    public SearchRestResponse<OrderRsp> query(SearchRestRequest<OrderQryReq> req) {

        OrderQry orderQry = OrderAppAssembler.toQry(req.getParam());
        PageableResult<OrderAggregate> orderPageableResult = orderDomainService.query(PageableParam.of(req.getPage(), orderQry));

        // todo 商品查询

        List<OrderRsp> orderRspList = OrderAppAssembler.toRsp(orderPageableResult.getRecords());
        return SearchRestResponse.ok(orderPageableResult.getPage(), orderRspList);
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

    @Override
    public void exportData(HttpServletResponse response, SearchRestRequest<OrderQryReq> req) {

        String filename = "订单导出";
        ServletOutputStream outputStream = ExcelUtils.getOutputStream(response, filename);

        // todo 使用函数式接口传递 工具类
        SearchRestResponse<OrderRsp> pagedRestResp = this.query(req);

        List<OrderExp> results = OrderAppAssembler.toExp(pagedRestResp.getData());

        EasyExcel.write(outputStream, OrderExp.class)
                .sheet("订单")
                .doWrite(() -> {
                    // 分页查询数据 todo
                    return results;
                });
    }
}
