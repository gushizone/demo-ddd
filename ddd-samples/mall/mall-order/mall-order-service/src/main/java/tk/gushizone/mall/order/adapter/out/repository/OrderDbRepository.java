package tk.gushizone.mall.order.adapter.out.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tk.gushizone.mall.order.adapter.out.repository.dto.OrderCreateDmResult;
import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;
import tk.gushizone.mall.order.domain.repository.OrderRepository;
import tk.gushizone.mall.order.infrastructure.repository.db.mapper.OrderItemMapper;
import tk.gushizone.mall.order.infrastructure.repository.db.mapper.OrderMapper;

import javax.annotation.Resource;

/**
 * @author gushizone
 * @date 2022/10/18 17:46
 */
@Repository
public class OrderDbRepository implements OrderRepository {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(OrderCreateCmdResult orderCreateCmdResult) {

        OrderCreateDmResult result = OrderCreateDmResult.valueOf(orderCreateCmdResult);

        orderMapper.insert(result.getOrder());

        orderItemMapper.saveBatch(result.getOrderItems());

        return result.getOrder().getId();
    }
}
