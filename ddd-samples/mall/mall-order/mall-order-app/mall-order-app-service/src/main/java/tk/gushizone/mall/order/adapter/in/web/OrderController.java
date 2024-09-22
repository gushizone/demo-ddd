package tk.gushizone.mall.order.adapter.in.web;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.base.entity.CommandRecord;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.infra.libs.core.rest.SearchRestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.excel.imp.OrderImp;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderModifyCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.application.service.OrderQryAppService;
import tk.gushizone.mall.order.infrastructure.util.ExcelUtils;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 11:40
 */
@Slf4j
@Tag(name = "订单")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderCmdAppService orderCmdAppService;
    @Resource
    private OrderQryAppService orderQryAppService;

    @Operation(summary = "创建")
    @PostMapping
    public RestResponse<Long> create(@RequestBody @Validated OrderCreateCmdReq req) {
        return orderCmdAppService.create(req);
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    public RestResponse<OrderRsp> query(@PathVariable("id") Long id) {
        return orderQryAppService.query(id);
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public RestResponse<Long> modify(@PathVariable("id") Long id,
                                     @RequestBody @Validated OrderModifyCmdReq req) {
        return orderCmdAppService.modify(req);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse<Void> delete(@PathVariable("id") Long id,
                                     @RequestBody @Validated CommandRecord req) {
        orderCmdAppService.delete(req);
        return RestResponse.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping("/delete")
    public RestResponse<List<Long>> delete(@RequestBody @Validated List<CommandRecord> req) {
        orderCmdAppService.delete(req);
        return RestResponse.ok();
    }

    @Operation(summary = "搜索")
    @PostMapping("/search")
    public SearchRestResponse<OrderRsp> search(@RequestBody @Validated SearchRestRequest<OrderQryReq> req) {
        return orderQryAppService.query(req);
    }

    @Operation(summary = "导出")
    @PostMapping("/export")
    public void exportData(HttpServletResponse response,
                           @RequestBody @Validated SearchRestRequest<OrderQryReq> req) {
        orderQryAppService.exportData(response, req);
    }

    @Operation(summary = "获取导入模板")
    @GetMapping("/import-tpl")
    public void createImportTpl(HttpServletResponse response) {
        String filename = "订单导入模板";
        ServletOutputStream outputStream = ExcelUtils.getOutputStream(response, filename);
        EasyExcel.write(outputStream, OrderImp.class)
                .sheet("订单")
                .doWrite(Lists.newArrayList());
    }

    @Operation(summary = "导入")
    @PostMapping("/import")
    public void importData(MultipartFile file) {
        orderCmdAppService.importData(file);
    }


}
