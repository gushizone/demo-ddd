package tk.gushizone.infra.libs.base.constant;

/**
 * 数据列
 *
 * @author gushizone
 * @since 2024/9/19
 */
public interface DbColumns {

    /**
     * 主键
     */
    String ID = "id";
    /**
     * 创建人
     */
    String CREATED_BY = "created_by";
    /**
     * 创建时间
     */
    String CREATED_AT = "created_at";
    /**
     * 更新人
     */
    String UPDATED_BY = "updated_by";
    /**
     * 更新时间
     */
    String UPDATED_AT = "updated_at";
    /**
     * 版本号:乐观锁
     */
    String REVISION = "revision";
    /**
     * 删除事件:逻辑删除
     */
    String DELETED_AT = "deleted_at";
}
