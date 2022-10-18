package tk.gushizone.mall.order.infrastructure.gateway.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import tk.gushizone.mall.order.infrastructure.gateway.db.dataobject.Order;
import tk.gushizone.mall.order.infrastructure.gateway.db.mapper.OrderMapper;
import tk.gushizone.mall.order.infrastructure.gateway.db.service.OrderDaoService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OrderDaoServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderDaoService {

}




