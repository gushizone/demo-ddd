package tk.gushizone.infra.libs.core.rest;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import tk.gushizone.infra.libs.base.BaseEnum;
import tk.gushizone.infra.libs.base.Status;
import tk.gushizone.infra.libs.base.exception.BusinessException;

public class WebTest {



    @Test
    public void restTest() {

        RestResponse<Void> rsp1 = RestResponse.ok();
        RestResponse<String> rsp2 = RestResponse.ok("123");

        System.out.println(rsp1);
        System.out.println(rsp2);

        try {
            throw new BusinessException("当前数据不存在: {}", 123);
        } catch (BusinessException ex) {
//            RestResponse<Void> rsp3 = new RestResponse<>(ex.getCode(), ex.getMessage());
//            System.out.println(rsp3);
            RestResponse<Void> rsp4 = RestResponse.fail(ex.getMessage());
            System.out.println(rsp4);
        }
    }

    @Test
    public void enumTest() {
        System.out.println(BaseEnum.codeOf(Status.class, 2000));
        System.out.println(Status.valueOf(Status.class,"OK"));
    }

    @Test
    public void pageTest() {
        Page<Object> page = new Page<>();

    }




}