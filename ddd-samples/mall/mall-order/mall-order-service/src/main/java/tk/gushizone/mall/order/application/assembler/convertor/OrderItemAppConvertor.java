package tk.gushizone.mall.order.application.assembler.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.application.dto.rsp.OrderItemRsp;
import tk.gushizone.mall.order.domain.model.cmd.OrderItemCmd;
import tk.gushizone.mall.order.adapter.out.remote.dto.Product;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/19 11:25
 */
@Mapper
public interface OrderItemAppConvertor {

    OrderItemAppConvertor INSTANCE = Mappers.getMapper(OrderItemAppConvertor.class);

    List<OrderItemRsp> toRsp(List<OrderItemEntity> orderItems);
}
