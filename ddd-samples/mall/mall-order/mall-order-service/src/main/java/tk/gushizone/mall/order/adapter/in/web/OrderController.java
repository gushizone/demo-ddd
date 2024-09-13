package tk.gushizone.mall.order.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.application.service.OrderQueryAppService;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 11:40
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
    public RestResponse<Long> create(@RequestBody @Validated OrderCreateCmdReq req) {
        return orderCmdAppService.create(req);
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    public RestResponse<OrderRsp> query(@PathVariable("id") Long id) {
        return orderQueryAppService.query(id);
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
    public PagedRestResponse<OrderRsp> search(@RequestBody @Validated PagingRestRequest<OrderQryReq> req) {
        return orderQueryAppService.query(req);
    }

    /**
     * todo
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody @Validated PagingRestRequest<OrderQryReq> req) {
        orderCmdAppService.exportData(response, req);
    }

    /**
     * todo
     */
    @Operation(summary = "获取导入模板")
    @GetMapping("/import-tpl")
    public void createImportTpl(HttpServletResponse response) {
        orderCmdAppService.createImportTpl(response);
    }

    /**
     * todo
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public void importData(MultipartFile file) {
        orderCmdAppService.importData(file);
    }


}
