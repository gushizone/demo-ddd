package tk.gushizone.mall.stock.application.assembler.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tk.gushizone.mall.stock.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.stock.domain.model.value.cmd.OrderCreateCmd;

/**
 * @author gushizone
 * @date 2022/10/18 18:04
 */
@Mapper
//@Mapper(componentModel = "spring")
public interface OrderConvertor {

    OrderConvertor INSTANCE = Mappers.getMapper(OrderConvertor.class);

    OrderCreateCmd reqToCmd(OrderCreateCmdReq req);



}
