package tk.gushizone.infra.libs.base.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 带版本号的实体
 * -
 * @author gushizone
 * @since 2024/9/16
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class RevisionEntity extends BaseEntity {

    /**
     * 版本号:乐观锁
     */
    private Integer revision;
}
