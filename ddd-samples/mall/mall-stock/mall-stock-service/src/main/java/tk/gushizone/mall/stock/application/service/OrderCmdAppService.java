package tk.gushizone.mall.stock.application.service;

import tk.gushizone.mall.stock.application.dto.req.cmd.OrderCreateCmdReq;

/**
 * @author gushizone
 * @date 2022/10/18 16:25
 */
public interface OrderCmdAppService {


    Long create(OrderCreateCmdReq req);
}
