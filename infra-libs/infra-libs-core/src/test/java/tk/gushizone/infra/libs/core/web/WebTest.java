package tk.gushizone.infra.libs.core.web;


import org.junit.jupiter.api.Test;
import tk.gushizone.infra.libs.core.exception.BusinessException;

class WebTest {



    @Test
    public void restTest() {

        RestResponse<Void> rsp1 = RestResponse.ok();
        RestResponse<String> rsp2 = RestResponse.ok("123");

        System.out.println(rsp1);
        System.out.println(rsp2);

        try {
            throw new BusinessException("当前数据不存在");
        } catch (BusinessException ex) {
            RestResponse<Void> rsp3 = new RestResponse<>(ex.getCode(), ex.getMessage());
            System.out.println(rsp3);
        }

    }




}