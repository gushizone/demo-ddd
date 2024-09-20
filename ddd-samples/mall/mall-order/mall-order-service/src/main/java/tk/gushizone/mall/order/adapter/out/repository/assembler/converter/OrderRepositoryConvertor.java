package tk.gushizone.mall.order.adapter.out.repository.assembler.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.order.domain.model.entity.Order;
import tk.gushizone.mall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 18:04
 */
@Mapper
public interface OrderRepositoryConvertor {

    OrderRepositoryConvertor INSTANCE = Mappers.getMapper(OrderRepositoryConvertor.class);

    @Mapping(target = "orderItems", ignore = true)
    OrderAggregate toAgg(Order order);

    List<OrderItem> toEntity(List<OrderItem> orDefault);
}
