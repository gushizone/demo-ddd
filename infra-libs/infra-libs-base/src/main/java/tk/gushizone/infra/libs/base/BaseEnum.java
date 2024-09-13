package tk.gushizone.infra.libs.base;

/**
 *
 * @author gushizone
 * @since 2023/6/2
 */
public interface BaseEnum {

    long code();

    String label();

    static <T extends BaseEnum> T codeOf(Class<T> enumClazz, Long code) {
        if (code == null) {
            return null;
        }
        for (T e : enumClazz.getEnumConstants()) {
            if (e.code() == code) {
                return e;
            }
        }
        return null;
    }

}
