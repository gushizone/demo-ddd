package tk.gushizone.mall.order.application.assembler;

import org.mapstruct.Mapper;
import tk.gushizone.mall.order.application.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.domain.model.value.cmd.OrderCreateCmd;

/**
 * @author gushizone
 * @date 2022/10/18 18:04
 */
@Mapper(componentModel = "spring")
public interface OrderAssembler {

    OrderCreateCmd reqToCmd(OrderCreateCmdReq req);

}
