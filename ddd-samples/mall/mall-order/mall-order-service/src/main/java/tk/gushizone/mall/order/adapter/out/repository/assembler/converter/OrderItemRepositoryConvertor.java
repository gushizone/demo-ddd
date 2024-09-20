package tk.gushizone.mall.order.adapter.out.repository.assembler.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:04
 */
@Mapper
public interface OrderItemRepositoryConvertor {

    OrderItemRepositoryConvertor INSTANCE = Mappers.getMapper(OrderItemRepositoryConvertor.class);

    OrderItem toPo(OrderItem orderItem);

    List<OrderItem> toPo(List<OrderItem> orderItems);

    List<OrderItem> toPoList(List<OrderItem> list);

}
