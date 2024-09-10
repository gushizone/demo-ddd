package tk.gushizone.infra.libs.core.util;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import tk.gushizone.infra.libs.base.util.ModelUtils;
import tk.gushizone.infra.libs.core.common.User;

import java.util.List;
import java.util.Map;

public class ModelUtilsTest {

    @Test
    public void test1() {

        List<User> list = Lists.newArrayList();
        User user1 = new User(1L, "aaa");
        list.add(user1);
        User user2 = new User(1L, "bbbb");
        list.add(user2);

        Map<Long, User> map = ModelUtils.toMap(list, User::getId);

    }


}