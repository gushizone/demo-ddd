package tk.gushizone.mall.order.application.service;

import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.mall.order.application.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.application.dto.rsp.OrderRsp;

/**
 * @author gushizone
 * @date 2022/10/18 16:26
 */
public interface OrderQueryAppService {

    PagedResult<OrderRsp> query(PagingParam<OrderQryReq> req);

}
