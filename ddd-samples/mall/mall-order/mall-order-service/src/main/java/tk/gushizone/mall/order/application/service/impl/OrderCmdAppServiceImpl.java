package tk.gushizone.mall.order.application.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.order.adapter.out.remote.ProductClient;
import tk.gushizone.mall.order.adapter.out.remote.dto.Product;
import tk.gushizone.mall.stock.api.StockApi;
import tk.gushizone.mall.stock.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import jakarta.annotation.Resource;
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
    private ProductClient productClient;

    @Resource
    private StockApi stockApi;


    @Override
    public Long create(OrderCreateCmdReq req) {

        // 商品 todo 在 stock
        List<Long> productIds = ModelUtils.map(req.getOrderItems(), OrderItemCmdReq::getProductId);
        List<Product> products = productClient.queryByIds(productIds);
        Map<Long, Product> productMap = ModelUtils.toMap(products, Product::getId);

        // 库存
        // todo
        StockQryApiReq stockQryReq = new StockQryApiReq();
        stockQryReq.setProductIds(productIds);
        List<StockApiRsp> stocksRsp = stockApi.query(stockQryReq).getData();
        Map<Long, StockApiRsp> productStockMap = ModelUtils.toMap(stocksRsp, StockApiRsp::getProductId);

        OrderCreateCmd orderCreateCmd = OrderAppAssembler.toCmd(req, productMap, productStockMap);
        Long id = orderDomainService.create(orderCreateCmd);

        // todo event...

        return id;
    }
}
