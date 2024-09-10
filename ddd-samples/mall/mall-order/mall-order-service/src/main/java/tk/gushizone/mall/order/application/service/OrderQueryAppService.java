package tk.gushizone.mall.order.application.service;

import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.mall.order.application.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.application.dto.rsp.OrderRsp;

/**
 * @author gushizone
 * @date 2022/10/18 16:26
 */
public interface OrderQueryAppService {

    PagedRestResponse<OrderRsp> query(PagingRestRequest<OrderQryReq> req);

}
