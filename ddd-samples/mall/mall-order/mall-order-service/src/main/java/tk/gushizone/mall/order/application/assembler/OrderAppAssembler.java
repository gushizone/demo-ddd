package tk.gushizone.mall.order.application.assembler;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.exception.BizException;
import tk.gushizone.infra.libs.core.auth.LoginUser;
import tk.gushizone.mall.order.adapter.in.web.dto.excel.exp.OrderExp;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.adapter.out.external.dto.ProductApiRsp;
import tk.gushizone.mall.order.application.assembler.converter.OrderAppConvertor;
import tk.gushizone.mall.order.application.assembler.converter.OrderItemAppConvertor;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderItemCmd;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.order.infrastructure.enums.OrderAppErrors;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @since 2022/10/19 10:57
 */
public class OrderAppAssembler {

    public static OrderCreateCmd toCreateCmd(OrderCreateCmdReq req,
                                             Map<Long, ProductApiRsp> productMap,
                                             Map<Long, StockApiRsp> productStockMap,
                                             LoginUser loginUser) {
        OrderCreateCmd result = OrderAppConvertor.INSTANCE.toCmd(req);
        result.setUserId(loginUser.getUserId());

        List<OrderItemCmd> orderItems = Lists.newArrayListWithExpectedSize(req.getOrderItems().size());
        for (OrderItemCmdReq orderItem : req.getOrderItems()) {

            ProductApiRsp productApiRsp = productMap.get(orderItem.getProductId());
            StockApiRsp stock = productStockMap.get(orderItem.getProductId());

            orderItems.add(toOrderItemCmd(orderItem, productApiRsp, stock));
        }
        result.setOrderItems(orderItems);
        return result;
    }

    private static OrderItemCmd toOrderItemCmd(OrderItemCmdReq orderItemReq,
                                               ProductApiRsp productApiRsp,
                                               StockApiRsp stock) {
        if (productApiRsp == null) {
            throw BizException.of(OrderAppErrors.PRODUCT_NOT_FOUND);
        }
        if (stock == null) {
            throw BizException.of(OrderAppErrors.PRODUCT_NOT_FOUND);
        }
        OrderItemCmd orderItemCmd = new OrderItemCmd();
        orderItemCmd.setProductId(productApiRsp.getId());
        orderItemCmd.setProductName(productApiRsp.getName());
        orderItemCmd.setProductImage(productApiRsp.getImage());
        orderItemCmd.setCurrentUnitPrice(productApiRsp.getCurrentUnitPrice());
        orderItemCmd.setQuantity(orderItemReq.getQuantity());

        return orderItemCmd;
    }

    public static OrderQry toQry(OrderQryReq req) {
        OrderQry orderQry = new OrderQry();
        orderQry.setIds(req.getIds());
        return orderQry;
    }

    public static List<OrderRsp> toRsp(List<OrderAggregate> records) {
        if (CollectionUtils.isEmpty(records)) {
            return Lists.newArrayList();
        }
        List<OrderRsp> results = Lists.newArrayListWithCapacity(records.size());
        for (OrderAggregate item : records) {
            OrderRsp result = OrderAppConvertor.INSTANCE.toRsp(item.getRoot());
            result.setOrderItems(OrderItemAppConvertor.INSTANCE.toRsp(item.getOrderItems()));
            results.add(result);
        }
        return results;
    }

    public static List<OrderExp> toExp(List<OrderRsp> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<OrderExp> results = Lists.newArrayListWithExpectedSize(list.size());
        for (OrderRsp item : list) {
            OrderExp result = new OrderExp();
            result.setOrderNo(item.getOrderNo());
            result.setPayment(item.getPayment());
            result.setOrderStatus(item.getOrderStatus());
            results.add(result);
        }
        return results;
    }
}
