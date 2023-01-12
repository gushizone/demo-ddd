package tk.gushizone.mall.order.application.server;

import tk.gushizone.mall.order.infrastructure.gateway.server.dataobject.Product;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/19 10:17
 */
public interface ProductServer {

    List<Product> queryByIds(List<Long> productIds);
}
