package tk.gushizone.mall.stock.adapter.out.external;

import tk.gushizone.mall.stock.adapter.out.external.dto.Product;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/19 10:17
 */
public interface ProductClient {

    List<Product> queryByIds(List<Long> productIds);
}
