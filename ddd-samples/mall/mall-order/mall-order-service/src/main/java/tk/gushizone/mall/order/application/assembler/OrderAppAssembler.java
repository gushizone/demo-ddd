package tk.gushizone.mall.order.application.assembler;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import tk.gushizone.infra.libs.base.query.PagingParam;
import tk.gushizone.mall.order.application.assembler.convertor.OrderAppConvertor;
import tk.gushizone.mall.order.application.assembler.convertor.OrderItemAppConvertor;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.application.dto.req.qry.OrderQryReq;
import tk.gushizone.mall.order.application.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderItemCmd;
import tk.gushizone.mall.order.adapter.out.remote.dto.Product;
import tk.gushizone.mall.order.domain.model.qry.OrderQry;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @date 2022/10/19 10:57
 */
public class OrderAppAssembler {

    public static OrderCreateCmd toCmd(OrderCreateCmdReq req,
                                       Map<Long, Product> productMap,
                                       Map<Long, StockApiRsp> productStockMap) {

        OrderCreateCmd result = OrderAppConvertor.INSTANCE.toCmd(req);

        List<OrderItemCmd> orderItems = Lists.newArrayListWithExpectedSize(req.getOrderItems().size());
        for (OrderItemCmdReq orderItem : req.getOrderItems()) {

            Product product = productMap.get(orderItem.getProductId());
            StockApiRsp stock = productStockMap.get(orderItem.getProductId());

            orderItems.add(orderItemOf(orderItem, product, stock));
        }
        result.setOrderItems(orderItems);
        return result;
    }

    private static OrderItemCmd orderItemOf(OrderItemCmdReq orderItemReq,
                                            Product product,
                                            StockApiRsp stock) {
        OrderItemCmd orderItemCmd = new OrderItemCmd();
//        orderItemCmd.setUserId(); // todo user
        orderItemCmd.setProductId(product.getId()); // todo null check
        orderItemCmd.setProductName(product.getName());
        orderItemCmd.setProductImage(product.getImage());
        orderItemCmd.setCurrentUnitPrice(product.getCurrentUnitPrice());
        orderItemCmd.setQuantity(orderItemReq.getQuantity());
        orderItemCmd.setStockQty(stock.getQuantity()); // todo null check

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
        for (OrderAggregate record : records) {
            OrderRsp result = OrderAppConvertor.INSTANCE.toRsp(record);

            result.setOrderItems(OrderItemAppConvertor.INSTANCE.toRsp(record.getOrderItems()));

            results.add(result);
        }
        return results;
    }
}
