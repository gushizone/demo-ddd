package tk.gushizone.mall.order.application.service;

import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;

/**
 * @author gushizone
 * @since 2022/10/18 16:26
 */
public interface OrderQueryAppService {

    PagedRestResponse<OrderRsp> query(PagingRestRequest<OrderQryReq> req);

    RestResponse<OrderRsp> query(Long id);
}
