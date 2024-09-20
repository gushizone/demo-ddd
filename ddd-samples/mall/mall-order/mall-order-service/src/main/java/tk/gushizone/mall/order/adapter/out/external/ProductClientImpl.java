package tk.gushizone.mall.order.adapter.out.external;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import tk.gushizone.mall.order.adapter.out.external.dto.ProductApiRsp;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/19 10:19
 */
@Component
public class ProductClientImpl implements ProductClient {


    @Override
    public List<ProductApiRsp> queryByIds(List<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return Lists.newArrayList();
        }
        List<ProductApiRsp> results = Lists.newArrayListWithExpectedSize(productIds.size());
        for (Long productId : productIds) {

            ProductApiRsp result = new ProductApiRsp();
            result.setId(productId);
            result.setName("产品" + productId);
            result.setImage("www.demo" + productId + ".png");
            result.setCurrentUnitPrice(new BigDecimal("9.9"));

            results.add(result);
        }
        return results;
    }
}
