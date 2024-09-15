package tk.gushizone.infra.libs.core.auth;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gushizone
 * @since 2024/9/15
 */
@Data
@Accessors(chain = true)
public class LoginUser {

    public static final LoginUser GUEST = new LoginUser()
            .setUserId(0L)
            .setUsername("guest");

    private Long userId;

    private String username;

}
