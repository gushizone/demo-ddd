package tk.gushizone.infra.libs.base.value;

/**
 * 枚举工具类
 *
 * @author gushizone
 * @since 2024/9/15
 */
public class Enums {

    /**
     * 通过code获取枚举类
     */
    public static <T extends BaseEnum> T codeOf(Class<T> enumClass, Integer code) {
        if (code == null) {
            return null;
        }
        for (T e : enumClass.getEnumConstants()) {
            if (e.code() == code) {
                return e;
            }
        }
        return null;
    }

    /**
     * 通过code获取字典枚举类
     */
    public static <T extends BaseDict> T codeOf(Class<T> enumClass, BaseEnum parent, Integer code) {
        if (code == null) {
            return null;
        }
        for (T e : enumClass.getEnumConstants()) {
            if (e.parent() != parent) {
                continue;
            }
            if (e.code() == code) {
                return e;
            }
        }
        return null;
    }

}
