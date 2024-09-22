package tk.gushizone.mall.order.application.assembler.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.adapter.in.web.dto.rsp.OrderItemRsp;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/19 11:25
 */
@Mapper
public interface OrderItemAppConvertor {

    OrderItemAppConvertor INSTANCE = Mappers.getMapper(OrderItemAppConvertor.class);

    List<OrderItemRsp> toRsp(List<OrderItem> orderItems);
}
