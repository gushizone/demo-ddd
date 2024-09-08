package tk.gushizone.mall.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gushizone
 * @date 2022/10/18 11:20
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MallStockApplication {


    public static void main(String[] args) {
        SpringApplication.run(MallStockApplication.class, args);
    }


}
