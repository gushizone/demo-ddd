package tk.gushizone.mall.order.application.service;

import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.base.entity.CommandRecord;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderModifyCmdReq;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/18 16:25
 */
public interface OrderCmdAppService {


    RestResponse<Long> create(OrderCreateCmdReq req);

    RestResponse<Long> modify(OrderModifyCmdReq req);

    void delete(CommandRecord req);

    void delete(List<CommandRecord> req);

    void importData(MultipartFile file);
}
