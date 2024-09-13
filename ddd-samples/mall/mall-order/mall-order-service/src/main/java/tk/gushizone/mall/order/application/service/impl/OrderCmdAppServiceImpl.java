package tk.gushizone.mall.order.application.service.impl;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.rest.PagedRestResponse;
import tk.gushizone.infra.libs.core.rest.PagingRestRequest;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.excel.exp.OrderExp;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.adapter.out.external.ProductClient;
import tk.gushizone.mall.order.adapter.out.external.dto.Product;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.application.service.OrderQueryAppService;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.stock.api.StockApi;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @since 2022/10/18 16:28
 */
@Service
public class OrderCmdAppServiceImpl implements OrderCmdAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Resource
    private OrderQueryAppService orderQueryAppService;

    @Resource
    private ProductClient productClient;

    @Resource
    private StockApi stockApi;


    @Override
    public RestResponse<Long> create(OrderCreateCmdReq req) {

        // 商品 todo 在 stock
        List<Long> productIds = ModelUtils.map(req.getOrderItems(), OrderItemCmdReq::getProductId);
        List<Product> products = productClient.queryByIds(productIds);
        Map<Long, Product> productMap = ModelUtils.toMap(products, Product::getId);

        // 库存
        StockQryApiReq stockQryReq = new StockQryApiReq()
                .setProductIds(productIds);
        List<StockApiRsp> stocksRsp = stockApi.query(PagingRestRequest.of(stockQryReq)).getData();
        Map<Long, StockApiRsp> productStockMap = ModelUtils.toMap(stocksRsp, StockApiRsp::getProductId);

        OrderCreateCmd orderCreateCmd = OrderAppAssembler.toCmd(req, productMap, productStockMap);
        Long id = orderDomainService.create(orderCreateCmd);

        // todo event...

        return RestResponse.ok(id);
    }

    @Override
    public void exportData(HttpServletResponse response, PagingRestRequest<OrderQryReq> req) {

        String filename = "订单导出";
        ServletOutputStream outputStream = getOutputStream(response, filename);

        // todo 使用函数式接口传递 工具类
        PagedRestResponse<OrderRsp> pagedRestResp = orderQueryAppService.query(req);

        List<OrderExp> results = OrderAppAssembler.toExp(pagedRestResp.getData());

        EasyExcel.write(outputStream, OrderExp.class)
                .sheet("订单")
                .doWrite(() -> {
                    // 分页查询数据 todo
                    return results;
                });
    }

    @Override
    public void createImportTpl(HttpServletResponse response) {
        String filename = "订单导入模板";
        ServletOutputStream outputStream = getOutputStream(response, filename);

        EasyExcel.write(outputStream, OrderExp.class)
                .sheet("订单")
                .doWrite(Lists.newArrayList());
    }

    @Override
    public void importData(MultipartFile file) {
        // todo 数据导入
    }

    @SneakyThrows
    private static ServletOutputStream getOutputStream(HttpServletResponse response, String filename) {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        return outputStream;
    }
}
