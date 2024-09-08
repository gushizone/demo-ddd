package tk.gushizone.mall.order.domain.repository;

import tk.gushizone.mall.order.domain.model.cmd.OrderCreateCmdResult;

/**
 * @author gushizone
 * @date 2022/10/18 17:46
 */
public interface OrderRepository {

    Long save(OrderCreateCmdResult orderCreateCmdResult);

}
