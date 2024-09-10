package tk.gushizone.mall.order.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tk.gushizone.infra.libs.base.query.PagedResult;
import tk.gushizone.infra.libs.core.web.PagedRestResponse;
import tk.gushizone.infra.libs.core.web.PagingRequest;
import tk.gushizone.infra.libs.core.web.RestResponse;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.req.qry.OrderQryReq;
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
@Tag(name = "订单")
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderCmdAppService orderCmdAppService;
    @Resource
    private OrderQueryAppService orderQueryAppService;

    @Operation(summary = "创建")
    @PostMapping
    public RestResponse<Long> create(@RequestBody OrderCreateCmdReq req) {
        return RestResponse.ok(orderCmdAppService.create(req));
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    public RestResponse<Long> query(@PathVariable("id") Long id) {
        return null;
    }

    @Operation(summary = "编辑")
    @PutMapping("/{id}")
    public RestResponse<Long> edit(@PathVariable("id") Long id) {
        return null;
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse<Long> delete(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * todo
     */
    @Operation(summary = "批量删除")
    @DeleteMapping("/delete")
    public RestResponse<List<Long>> batchDelete(@RequestBody List<Long> ids) {
        return null;
    }


    @Operation(summary = "搜索")
    @PostMapping("/search")
    public PagedRestResponse<OrderRsp> query(@RequestBody PagingRequest<OrderQryReq> req) {

        PagedResult<OrderRsp> pagedResult = orderQueryAppService.query(req);
        return PagedRestResponse.ok(pagedResult);
    }

    /**
     * todo
     */
    @Operation(summary = "导出")
    @PostMapping("/export-file")
    public RestResponse<List<Long>> exportFile(@RequestBody OrderQryReq req) {
        return null;
    }

    /**
     * todo
     */
    @Operation(summary = "导入")
    @PostMapping("/import-file")
    public RestResponse<List<Long>> importFile() {
        return null;
    }




}
