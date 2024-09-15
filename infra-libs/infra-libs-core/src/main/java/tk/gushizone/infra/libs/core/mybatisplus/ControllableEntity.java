package tk.gushizone.infra.libs.core.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 可控的实体
 *
 * @author gushizone
 * @since 2024/9/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ControllableEntity extends BaseEntity {

    /**
     * 版本号:乐观锁
     */
    @Version
    private Integer revision;
    /**
     * 删除事件:逻辑删除
     */
    @TableLogic
    private Date deletedAt;

}
