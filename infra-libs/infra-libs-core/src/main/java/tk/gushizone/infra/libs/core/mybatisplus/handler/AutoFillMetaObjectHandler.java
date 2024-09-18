package tk.gushizone.infra.libs.core.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import tk.gushizone.infra.libs.base.constant.Fields;
import tk.gushizone.infra.libs.core.auth.LoginUser;
import tk.gushizone.infra.libs.core.auth.LoginUserHolder;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 自动填充属性
 *
 * @author gushizone
 * @since 2024/9/15
 */
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser loginUser = LoginUserHolder.getUser().orElse(LoginUser.GUEST);
        Date now = new Date();

        this.strictInsertFill(metaObject, Fields.CREATED_BY, Long.class, loginUser.getUserId());
        this.strictInsertFill(metaObject, Fields.CREATED_AT, Date.class, now);
        this.strictInsertFill(metaObject, Fields.UPDATED_BY, Long.class, loginUser.getUserId());
        this.strictInsertFill(metaObject, Fields.UPDATED_AT, Date.class, now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LoginUser loginUser = LoginUserHolder.getUser().orElse(LoginUser.GUEST);
        Date now = new Date();

        this.strictUpdateFill(metaObject, Fields.UPDATED_BY, Long.class, loginUser.getUserId());
        this.strictUpdateFill(metaObject, Fields.UPDATED_AT, Date.class, now);
    }

    /**
     * 严格模式填充策略,默认有值覆盖,如果提供的值为null也不填充
     */
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
}
