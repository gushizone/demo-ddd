package tk.gushizone.mall.order.application.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import tk.gushizone.infra.libs.core.rest.SearchRestRequest;
import tk.gushizone.infra.libs.core.rest.RestResponse;
import tk.gushizone.mall.order.adapter.in.web.dto.req.cmd.OrderCreateCmdReq;
import tk.gushizone.mall.order.adapter.in.web.dto.req.qry.OrderQryReq;

/**
 * @author gushizone
 * @since 2022/10/18 16:25
 */
public interface OrderCmdAppService {


    RestResponse<Long> create(OrderCreateCmdReq req);

    void delete(Long id);

    void exportData(HttpServletResponse response, SearchRestRequest<OrderQryReq> req);

    void createImportTpl(HttpServletResponse response);

    void importData(MultipartFile file);
}
