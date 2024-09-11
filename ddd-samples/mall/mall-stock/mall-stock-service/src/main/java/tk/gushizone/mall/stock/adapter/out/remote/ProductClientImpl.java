package tk.gushizone.mall.order.adapter.out.remote;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import tk.gushizone.mall.order.adapter.out.remote.dto.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gushizone
 * @since 2022/10/19 10:19
 */
@Component
public class ProductClientImpl implements ProductClient {


    @Override
    public List<Product> queryByIds(List<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return Lists.newArrayList();
        }
        List<Product> results = Lists.newArrayListWithExpectedSize(productIds.size());
        for (Long productId : productIds) {

            Product result = new Product();
            result.setId(productId);
            result.setName("产品" + productId);
            result.setImage("www.demo" + productId + ".png");
            result.setCurrentUnitPrice(RandomUtil.randomBigDecimal(new BigDecimal("1"), new BigDecimal("100")));

            results.add(result);
        }
        return results;
    }
}
