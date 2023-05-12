package tk.gushizone.mall.stock.infrastructure.gateway.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import tk.gushizone.mall.stock.infrastructure.gateway.db.dataobject.OrderItem;
import tk.gushizone.mall.stock.infrastructure.gateway.db.mapper.OrderItemMapper;
import tk.gushizone.mall.stock.infrastructure.gateway.db.service.OrderItemDaoService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OrderItemDaoServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemDaoService {

}




