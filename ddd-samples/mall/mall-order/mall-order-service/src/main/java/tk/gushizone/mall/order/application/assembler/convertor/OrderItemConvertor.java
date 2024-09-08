package tk.gushizone.mall.order.application.assembler.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.order.domain.model.cmd.OrderItemCmd;
import tk.gushizone.mall.order.adapter.out.remote.dto.Product;

/**
 * @author gushizone
 * @date 2022/10/19 11:25
 */
@Mapper
public interface OrderItemConvertor {

    OrderItemConvertor INSTANCE = Mappers.getMapper(OrderItemConvertor.class);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.image", target = "productImage")
    @Mapping(source = "quantity", target = "quantity")
    OrderItemCmd toCmd(Product product, Integer quantity);
}
