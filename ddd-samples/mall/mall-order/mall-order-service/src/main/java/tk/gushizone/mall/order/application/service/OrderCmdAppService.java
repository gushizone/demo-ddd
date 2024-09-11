package tk.gushizone.mall.order.application.service;

import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;

/**
 * @author gushizone
 * @since 2022/10/18 16:25
 */
public interface OrderCmdAppService {


    Long create(OrderCreateCmdReq req);
}
