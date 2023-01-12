package tk.gushizone.mall.order.application.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.utils.ModelUtils;
import tk.gushizone.mall.order.application.assembler.OrderFactory;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.application.service.OrderCmdAppService;
import tk.gushizone.mall.order.domain.model.value.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.OrderDomainService;
import tk.gushizone.mall.order.application.server.ProductServer;
import tk.gushizone.mall.order.infrastructure.gateway.server.dataobject.Product;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @date 2022/10/18 16:28
 */
@Service
public class OrderCmdAppServiceImpl implements OrderCmdAppService {

    @Resource
    private OrderDomainService orderDomainService;

    /**
     * todo api
     */
    @Resource
    private ProductServer productServer;


    @Override
    public Long create(OrderCreateCmdReq req) {

        // 商品
        List<Long> productIds = ModelUtils.map(req.getOrderItems(), OrderItemCmdReq::getProductId);
        List<Product> products = productServer.queryByIds(productIds);
        Map<Long, Product> productMap = ModelUtils.convertToMap(products, Product::getId);

        // todo 库存
        OrderCreateCmd cmd = OrderFactory.buildCmd(req, productMap);
        Long id = orderDomainService.create(cmd);

        // todo event...

        return id;
    }
}
