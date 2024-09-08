package tk.gushizone.mall.order.application.assembler;

import com.google.common.collect.Lists;
import tk.gushizone.mall.order.application.assembler.convertor.OrderConvertor;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.cmd.OrderItemCmd;
import tk.gushizone.mall.order.adapter.out.remote.dto.Product;
import tk.gushizone.mall.stock.dto.rsp.StockApiRsp;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @date 2022/10/19 10:57
 */
public class OrderFactory {

    public static OrderCreateCmd buildCmd(OrderCreateCmdReq req,
                                          Map<Long, Product> productMap,
                                          Map<Long, StockApiRsp> productStockMap) {

        OrderCreateCmd result = OrderConvertor.INSTANCE.reqToCmd(req);

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
        orderItemCmd.setQuantity(orderItemReq.getQuantity());
        orderItemCmd.setStockQty(stock.getQuantity()); // todo null check

        return orderItemCmd;
    }
}
