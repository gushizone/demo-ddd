package tk.gushizone.infra.libs.base.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Data
public class Paging implements Serializable {

    /***
     * 当前页
     */
    private long current = 1;
    /***
     * 每页数量, 默认为10
     */
    private long size = 10;

    private List<OrderItem> orders;


}