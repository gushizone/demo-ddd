package tk.gushizone.infra.libs.base.entity.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 排序项
 *
 * @author gushizone
 * @since 2024/9/19
 */
@Getter
@Setter
public class OrderEntry {

    /**
     * 排序列
     */
    private String column;

    /**
     * 排序
     */
    private String order;
}
