package tk.gushizone.mall.stock.domain.service;

import tk.gushizone.mall.stock.domain.model.value.cmd.OrderCreateCmd;

/**
 * @author gushizone
 * @date 2022/10/18 17:47
 */
public interface OrderDomainService {

    Long create(OrderCreateCmd cmd);
}
