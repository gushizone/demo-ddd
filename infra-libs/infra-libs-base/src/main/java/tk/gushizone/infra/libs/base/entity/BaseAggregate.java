package tk.gushizone.infra.libs.base.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author gushizone
 * @since 2024/9/20
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseAggregate<T extends BaseEntity> {

    private T root;

}
