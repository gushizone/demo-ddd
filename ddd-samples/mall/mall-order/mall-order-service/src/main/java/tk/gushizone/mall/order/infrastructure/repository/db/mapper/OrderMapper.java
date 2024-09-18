package tk.gushizone.mall.order.infrastructure.repository.db.mapper;

import tk.gushizone.mall.order.infrastructure.repository.db.po.Order;
import tk.gushizone.infra.libs.core.mybatisplus.BasePlusMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author gushizone
 * @since 2024-09-17
 */
@Mapper
public interface OrderMapper extends BasePlusMapper<Order> {

}
