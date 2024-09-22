package tk.gushizone.mall.order.application.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.base.entity.CommandParam;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;

/**
 * @author gushizone
 * @since 2022/10/18 16:25
 */
public interface OrderCmdAppService {


    RestResponse<Long> create(OrderCreateCmdReq req);

    void delete(CommandParam req);

    void importData(MultipartFile file);
}
