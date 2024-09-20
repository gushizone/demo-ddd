package tk.gushizone.mall.order.infrastructure.repository.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.gushizone.infra.libs.core.mybatisplus.BasePlusMapper;
import tk.gushizone.mall.order.domain.model.entity.Order;

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
