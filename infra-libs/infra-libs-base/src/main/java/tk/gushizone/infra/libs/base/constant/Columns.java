package tk.gushizone.infra.libs.base.constant;

/**
 * 字段
 *
 * @author gushizone
 * @since 2024/9/19
 */
public interface Columns {

    /**
     * 主键
     */
    String ID = "id";
    /**
     * 创建人
     */
    String CREATED_BY = "createdBy";
    /**
     * 创建时间
     */
    String CREATED_AT = "createdAt";
    /**
     * 更新人
     */
    String UPDATED_BY = "updatedBy";
    /**
     * 更新时间
     */
    String UPDATED_AT = "updatedAt";
    /**
     * 版本号:乐观锁
     */
    String REVISION = "revision";
    /**
     * 删除事件:逻辑删除
     */
    String DELETED_AT = "deletedAt";
}
