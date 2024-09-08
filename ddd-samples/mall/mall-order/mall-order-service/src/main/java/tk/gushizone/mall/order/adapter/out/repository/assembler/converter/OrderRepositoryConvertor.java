package tk.gushizone.mall.order.adapter.out.repository.assembler.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.domain.model.entity.OrderEntity;
import tk.gushizone.mall.order.infrastructure.repository.db.po.Order;

/**
 * @author gushizone
 * @date 2022/10/18 18:04
 */
@Mapper
public interface OrderRepositoryConvertor {

    OrderRepositoryConvertor INSTANCE = Mappers.getMapper(OrderRepositoryConvertor.class);

    Order toPo(OrderEntity req);
}
