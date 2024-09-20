package tk.gushizone.mall.order.application.assembler.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmd;

/**
 * @author gushizone
 * @since 2022/10/18 18:04
 */
@Mapper
public interface OrderAppConvertor {

    OrderAppConvertor INSTANCE = Mappers.getMapper(OrderAppConvertor.class);

    OrderCreateCmd toCmd(OrderCreateCmdReq req);

    OrderRsp toRsp(Order order);
}
