package tk.gushizone.infra.libs.core.web;

import lombok.Data;

/**
 * todo
 */
@Data
public class PagingParamRequest {

    /***
     * 当前页
     */
    private long current = 1;
    /***
     * 每页数量, 默认为10
     */
    private long size = 10;

    /**
     * 排序 todo
     * column:order => createBy:asc
     * column1:order1,column2:order2 => createBy:asc,createAt:desc
     */
    private String orderBy = "";
}
