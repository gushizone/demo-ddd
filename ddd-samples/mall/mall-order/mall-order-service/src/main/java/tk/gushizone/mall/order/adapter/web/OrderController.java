package tk.gushizone.mall.order.adapter.web;

import org.springframework.web.bind.annotation.*;
import tk.gushizone.infra.libs.core.web.PagingRestResponse;
import tk.gushizone.infra.libs.core.web.RestResponse;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.application.service.OrderQueryAppService;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author gushizone
 * @date 2022/10/18 11:40
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderCmdAppService orderCmdAppService;
    @Resource
    private OrderQueryAppService orderQueryAppService;

    @GetMapping
    public PagingRestResponse<List<OrderRsp>> page() {
        return null;
    }

    @PostMapping
    public RestResponse<Long> create(@RequestBody OrderCreateCmdReq req) {
        return RestResponse.ok(orderCmdAppService.create(req));
    }

    @PutMapping("/{id}")
    public RestResponse<Long> edit(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public RestResponse<Long> delete(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/batch/{ids}")
    public RestResponse<List<Long>> deleteByIds(@PathVariable("ids") Long ids) {
        return null;
    }


    /**
     * todo /orders/:search ???
     */
    @GetMapping("/orders:search")
    public Long search() {
        return null;
    }


}
