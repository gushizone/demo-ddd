package tk.gushizone.infra.libs.base.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 基础实体
 * - domain 使用
 *
 * @author gushizone
 * @since 2024/9/16
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新人
     */
    private Long updatedBy;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
