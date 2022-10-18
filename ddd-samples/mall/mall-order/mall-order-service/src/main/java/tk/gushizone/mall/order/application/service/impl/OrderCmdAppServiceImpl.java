package tk.gushizone.mall.order.application.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.mall.order.application.assembler.OrderAssembler;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.domain.model.value.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.OrderDomainService;

import javax.annotation.Resource;

/**
 * @author gushizone
 * @date 2022/10/18 16:28
 */
@Service
public class OrderCmdAppServiceImpl implements OrderCmdAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Resource
    private OrderAssembler orderAssembler;


    @Override
    public Long create(OrderCreateCmdReq req) {

        OrderCreateCmd cmd = orderAssembler.reqToCmd(req);
        Long id = orderDomainService.create(cmd);

        // todo event...

        return id;
    }
}
