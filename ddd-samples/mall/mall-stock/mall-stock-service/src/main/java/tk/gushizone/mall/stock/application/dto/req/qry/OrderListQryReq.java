package tk.gushizone.mall.stock.application.dto.req.qry;

import lombok.Data;

import java.util.Date;

/**
 * todo
 *
 * @author gushizone
 * @since 2022/10/18 15:12
 */
@Data
public class OrderListQryReq {

    private Date createTimeFrom;

    private Date createTimeTo;

}
