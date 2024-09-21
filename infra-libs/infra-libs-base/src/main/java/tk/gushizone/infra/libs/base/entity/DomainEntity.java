package tk.gushizone.infra.libs.base.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 领域实体
 * - 带版本号
 *
 * @author gushizone
 * @since 2024/9/15
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class DomainEntity extends BaseEntity {

    /**
     * 版本号:乐观锁
     */
    @Version
    private Integer version;
}
