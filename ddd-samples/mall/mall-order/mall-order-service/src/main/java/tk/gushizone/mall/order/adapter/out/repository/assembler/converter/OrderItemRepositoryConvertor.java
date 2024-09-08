package tk.gushizone.mall.order.adapter.out.repository.assembler.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.domain.model.entity.OrderItemEntity;
import tk.gushizone.mall.order.infrastructure.repository.db.po.OrderItem;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 18:04
 */
@Mapper
public interface OrderItemRepositoryConvertor {

    OrderItemRepositoryConvertor INSTANCE = Mappers.getMapper(OrderItemRepositoryConvertor.class);

    OrderItem toPo(OrderItemEntity orderItem);

    List<OrderItem> toPoList(List<OrderItemEntity> list);
}
