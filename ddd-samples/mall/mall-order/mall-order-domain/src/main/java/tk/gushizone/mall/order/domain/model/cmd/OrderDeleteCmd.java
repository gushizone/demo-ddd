package tk.gushizone.mall.order.domain.model.cmd;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * todo 通用抽取
 *
 * @author gushizone
 * @since 2022/10/18 18:01
 */
@Data
@Accessors(chain = true)
public class OrderDeleteCmd {

    private List<Long> ids;

}
