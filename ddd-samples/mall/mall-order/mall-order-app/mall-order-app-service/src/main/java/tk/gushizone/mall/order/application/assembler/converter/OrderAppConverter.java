package tk.gushizone.mall.order.application.assembler.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderModifyCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderRsp;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderCreateCmd;
import tk.gushizone.mall.order.domain.service.dto.cmd.OrderModifyCmd;

/**
 * @author gushizone
 * @since 2022/10/18 18:04
 */
@Mapper
public interface OrderAppConverter {

    OrderAppConverter INSTANCE = Mappers.getMapper(OrderAppConverter.class);

    OrderCreateCmd toCmd(OrderCreateCmdReq req);

    OrderRsp toRsp(Order order);

    OrderModifyCmd toCmd(OrderModifyCmdReq req);
}
