package tk.gushizone.mall.order.infrastructure.repository.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.gushizone.infra.libs.core.mybatisplus.BasePlusMapper;
import tk.gushizone.mall.order.infrastructure.repository.db.po.OrderItem;

/**
 * @author gushizone
 * @since 2024/9/7
 */
@Mapper
public interface OrderItemMapper extends BasePlusMapper<OrderItem> {

}




