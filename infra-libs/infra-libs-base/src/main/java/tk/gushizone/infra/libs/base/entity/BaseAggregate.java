package tk.gushizone.infra.libs.base.entity;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 基础的聚合
 *
 * @author gushizone
 * @since 2024/9/20
 */
@Getter
@Accessors(chain = true)
public abstract class BaseAggregate<T extends DomainEntity> {

    protected T root;

    protected BaseAggregate(T root) {
        this.root = root;
    }

    public Long getId() {
        return this.root.getId();
    }

    public Integer getVersion() {
        return this.root.getVersion();
    }

    public void setId(Long id) {
        this.root.setId(id);
    }

    public void setVersion(Integer version) {
        this.root.setVersion(version);
    }
}
