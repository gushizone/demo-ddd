package tk.gushizone.mall.order.adapter.out.external;

import tk.gushizone.mall.order.adapter.out.external.dto.ProductApiRsp;

import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/19 10:17
 */
public interface ProductClient {

    List<ProductApiRsp> queryByIds(List<Long> productIds);
}
