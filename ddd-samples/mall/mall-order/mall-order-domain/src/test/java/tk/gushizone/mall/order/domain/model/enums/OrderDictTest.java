package tk.gushizone.mall.order.domain.model.enums;


import org.junit.jupiter.api.Test;
import tk.gushizone.infra.libs.base.value.Enums;

class OrderDictTest {

    @Test
    public void test() {

        System.out.println(Enums.codeOf(OrderDict.class, OrderDict.ORDER_STATUS, 1002_1001));
        System.out.println(Enums.codeOf(OrderDict.class, OrderDict.ORDER_STATUS, 1001_1001));

    }

}