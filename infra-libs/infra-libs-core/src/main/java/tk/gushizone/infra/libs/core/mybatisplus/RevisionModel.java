package tk.gushizone.infra.libs.core.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 版本数据模型
 *
 * @author gushizone
 * @since 2024/9/15
 */
@Getter
@Setter
@Accessors(chain = true)
public class RevisionModel extends BaseModel {

    /**
     * 版本号:乐观锁
     */
    @Version
    private Integer revision;
}
