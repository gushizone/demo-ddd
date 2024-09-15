package tk.gushizone.infra.libs.core.auth;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

/**
 * @author gushizone
 * @since 2024/9/15
 */
public class LoginUserHolder {

    private static final TransmittableThreadLocal<LoginUser> USER_HOLDER = new TransmittableThreadLocal<>();

    public static void setUser(LoginUser loginUser) {
        USER_HOLDER.set(loginUser);
    }

    public static Optional<LoginUser> getUser() {
        return Optional.ofNullable(USER_HOLDER.get());
    }

    public static void remove() {
        USER_HOLDER.remove();
    }


}
