package tk.gushizone.mall.order.adapter.in.web.dto.excel.imp;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import tk.gushizone.infra.libs.base.validation.constraints.Dict;
import tk.gushizone.mall.order.domain.api.contant.OrderDictTypes;
import tk.gushizone.mall.order.domain.api.enums.OrderDict;

import java.math.BigDecimal;

/**
 * @author gushizone
 * @since 2022/10/18 15:51
 */
@Data
public class OrderImp {

    @ExcelProperty(value = "订单号")
    private String orderNo;

    @ExcelProperty(value = "实付金额")
    private BigDecimal payment;

    @ExcelProperty(value = "订单状态")
    @Dict(dict = OrderDict.class, type = OrderDictTypes.ORDER_STATUS)
    private Integer orderStatus;

}
