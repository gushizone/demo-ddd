package tk.gushizone.mall.order.application.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.base.entity.CommandRecord;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.auth.LoginUser;
import tk.gushizone.infra.libs.core.auth.LoginUserHolder;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.mall.order.adapter.in.web.dto.excel.imp.OrderImp;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderModifyCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.adapter.out.external.ProductClient;
import tk.gushizone.mall.order.adapter.out.external.dto.ProductApiRsp;
import tk.gushizone.mall.order.application.assembler.OrderAppAssembler;
import tk.gushizone.mall.order.application.assembler.converter.OrderAppConverter;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.application.service.OrderQryAppService;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderDeleteCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderModifyCmd;
import tk.gushizone.mall.stock.api.StockApi;
import tk.gushizone.mall.stock.api.dto.req.qry.StockQryApiReq;
import tk.gushizone.mall.stock.api.dto.rsp.StockApiRsp;

import java.util.List;
import java.util.Map;

/**
 * todo 分布式事务控制
 *
 * @author gushizone
 * @since 2022/10/18 16:28
 */
@Slf4j
@Service
public class OrderCmdAppServiceImpl implements OrderCmdAppService {

    @Resource
    private OrderDomainService orderDomainService;

    @Resource
    private OrderQryAppService orderQryAppService;

    @Resource
    private ProductClient productClient;

    @Resource
    private StockApi stockApi;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<Long> create(OrderCreateCmdReq req) {

        // 用户
        LoginUser loginUser = LoginUserHolder.getUser().orElse(LoginUser.GUEST);

        // 商品
        List<Long> productIds = ModelUtils.map(req.getOrderItems(), OrderItemCmdReq::getProductId);
        List<ProductApiRsp> products = productClient.queryByIds(productIds);
        Map<Long, ProductApiRsp> productMap = ModelUtils.toMap(products, ProductApiRsp::getId);

        // 库存
        StockQryApiReq stockQryReq = new StockQryApiReq()
                .setProductIds(productIds);
        List<StockApiRsp> stocksRsp = stockApi.query(SearchRestRequest.of(stockQryReq)).getData();
        Map<Long, StockApiRsp> productStockMap = ModelUtils.toMap(stocksRsp, StockApiRsp::getProductId);

        OrderCreateCmd orderCreateCmd = OrderAppAssembler.toCreateCmd(req, productMap, productStockMap, loginUser);
        Long id = orderDomainService.create(orderCreateCmd);

        // todo event...

        return RestResponse.ok(id);
    }

    @Override
    public RestResponse<Long> modify(OrderModifyCmdReq req) {
        OrderModifyCmd cmd = OrderAppConverter.INSTANCE.toCmd(req);
        Long id = orderDomainService.modify(cmd);
        return RestResponse.ok(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CommandRecord req) {
        orderDomainService.delete(new OrderDeleteCmd().setRecords(Lists.newArrayList(req)));
    }

    @Override
    public void delete(List<CommandRecord> req) {
        orderDomainService.delete(new OrderDeleteCmd().setRecords(req));
    }

    @SneakyThrows
    @Override
    public void importData(MultipartFile file) {
        // todo 数据导入

        List<OrderImp> orderImpList = Lists.newArrayList();
        EasyExcel.read(file.getInputStream(), OrderImp.class, new AnalysisEventListener<OrderImp>() {
                    @Override
                    public void invoke(OrderImp data, AnalysisContext context) {
                        orderImpList.add(data);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                    }
                })
                .doReadAll();

        log.info("list: {}", JSONUtil.toJsonStr(orderImpList));

        // todo ...
        // check
        // imp -> createCmd
        // create
    }
}
