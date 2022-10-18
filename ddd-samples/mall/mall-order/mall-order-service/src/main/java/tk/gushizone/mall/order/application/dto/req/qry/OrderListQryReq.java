package tk.gushizone.mall.order.application.dto.req.qry;

import lombok.Data;
import tk.gushizone.mall.order.application.dto.req.cmd.common.OrderItemCmdReq;

import java.util.Date;
import java.util.List;

/**
 * todo
 *
 * @author gushizone
 * @date 2022/10/18 15:12
 */
@Data
public class OrderListQryReq {

    private Date createTimeFrom;

    private Date createTimeTo;

}
