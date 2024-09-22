package tk.gushizone.mall.order.adapter.out.api;

import org.springframework.cloud.openfeign.FeignClient;
import tk.gushizone.mall.stock.api.StockApi;

@FeignClient(name = "mall-stock", contextId = "stock")
public interface StockApiClient extends StockApi {

}
