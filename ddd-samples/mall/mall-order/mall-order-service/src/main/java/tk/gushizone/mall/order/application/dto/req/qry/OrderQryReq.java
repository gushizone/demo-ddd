package tk.gushizone.mall.order.application.dto.req.qry;

import lombok.Data;

import java.util.Date;

/**
 * todo
 *
 * @author gushizone
 * @date 2022/10/18 15:12
 */
@Data
public class OrderQryReq {

    private Date createTimeFrom;

    private Date createTimeTo;

}
