package tk.gushizone.infra.libs.core.common;

public interface BaseEnum {

    int code();

    String label();

    static <T extends BaseEnum> T codeOf(Class<T> enumClazz, Integer code) {
        for (T e : enumClazz.getEnumConstants()) {
            if (e.code() == code) {
                return e;
            }
        }
        return null;
    }

}
