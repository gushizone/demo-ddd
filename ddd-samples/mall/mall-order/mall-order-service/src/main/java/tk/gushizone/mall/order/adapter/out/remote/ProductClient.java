package tk.gushizone.mall.order.adapter.out.remote;

import tk.gushizone.mall.order.adapter.out.remote.dto.Product;

import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/19 10:17
 */
public interface ProductClient {

    List<Product> queryByIds(List<Long> productIds);
}
