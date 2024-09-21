package tk.gushizone.infra.libs.base.entity;

/**
 * 带版本的记录  todo
 * - 增删改基础条件
 *
 * @author gushizone
 * @since 2024/9/16
 */
public interface RevisionRecord {

    Long getId();

    void setId(Long id);

    Integer getVersion();

    void setVersion(Integer revision);
}
