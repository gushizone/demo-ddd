package tk.gushizone.mall.stock.application.server;

import tk.gushizone.mall.stock.infrastructure.gateway.server.dataobject.Product;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/19 10:17
 */
public interface ProductServer {

    List<Product> queryByIds(List<Long> productIds);
}
