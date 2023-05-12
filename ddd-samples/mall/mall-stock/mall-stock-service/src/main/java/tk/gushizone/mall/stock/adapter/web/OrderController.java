package tk.gushizone.mall.stock.adapter.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.mall.stock.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.stock.application.service.OrderCmdAppService;
import tk.gushizone.mall.stock.application.service.OrderQueryAppService;

import javax.annotation.Resource;

/**
 *
 * @author gushizone
 * @date 2022/10/18 11:40
 */
@RestController
@RequestMapping("/mall-order")
public class OrderController {

    @Resource
    private OrderCmdAppService orderCmdAppService;
    @Resource
    private OrderQueryAppService orderQueryAppService;


    @PostMapping("/orders")
    public Long create(@RequestBody OrderCreateCmdReq req) {
        return orderCmdAppService.create(req);
    }

    /**
     * todo /orders/:search ???
     */
    @GetMapping("/orders:search")
    public Long search() {
        return null;
    }


}
