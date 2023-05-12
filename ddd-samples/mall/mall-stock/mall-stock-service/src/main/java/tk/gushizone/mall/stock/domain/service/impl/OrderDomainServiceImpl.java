package tk.gushizone.mall.stock.domain.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.mall.stock.domain.model.aggregate.OrderAggregate;
import tk.gushizone.mall.stock.domain.model.value.cmd.OrderCreateCmd;
import tk.gushizone.mall.stock.domain.repository.OrderRepository;
import tk.gushizone.mall.stock.domain.service.OrderDomainService;

import javax.annotation.Resource;

/**
 * @author gushizone
 * @date 2022/10/18 17:47
 */
@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public Long create(OrderCreateCmd cmd) {

        // todo
        OrderAggregate order = new OrderAggregate();
//        orderRepository.save(order);

        return null;
    }
}
