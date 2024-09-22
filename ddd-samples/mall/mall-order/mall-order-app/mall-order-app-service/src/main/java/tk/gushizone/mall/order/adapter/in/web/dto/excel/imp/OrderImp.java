package tk.gushizone.mall.order.adapter.in.web.dto.excel.imp;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gushizone
 * @since 2022/10/18 15:51
 */
@Data
public class OrderImp {

    @ExcelProperty(value = "订单号")
    private Long orderNo;

    @ExcelProperty(value = "实付金额")
    private BigDecimal payment;

    /**
     * todo 字典
     */
    @ExcelProperty(value = "订单状态")
    private Integer orderStatus;

}