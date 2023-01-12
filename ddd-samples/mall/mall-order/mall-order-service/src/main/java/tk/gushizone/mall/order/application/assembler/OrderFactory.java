package tk.gushizone.mall.order.application.assembler;

import com.google.common.collect.Lists;
import tk.gushizone.mall.order.application.assembler.convertor.OrderConvertor;
import tk.gushizone.mall.order.application.assembler.convertor.OrderItemConvertor;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;
import tk.gushizone.mall.order.domain.model.value.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.model.value.cmd.common.OrderItemCmd;
import tk.gushizone.mall.order.infrastructure.gateway.server.dataobject.Product;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @date 2022/10/19 10:57
 */
public class OrderFactory {

    public static OrderCreateCmd buildCmd(OrderCreateCmdReq req,
                                          Map<Long, Product> productMap) {

        OrderCreateCmd result = OrderConvertor.INSTANCE.reqToCmd(req);

        List<OrderItemCmd> orderItems = Lists.newArrayListWithExpectedSize(req.getOrderItems().size());
        for (OrderItemCmdReq orderItem : req.getOrderItems()) {
            Product product = productMap.get(orderItem.getProductId());
            orderItems.add(OrderItemConvertor.INSTANCE.toCmd(product, orderItem.getQuantity()));
        }
        result.setOrderItems(orderItems);
        return result;
    }
}
