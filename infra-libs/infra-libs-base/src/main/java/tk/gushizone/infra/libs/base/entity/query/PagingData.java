package tk.gushizone.infra.libs.base.entity.query;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
@Getter
@Setter
@Schema(description = "分页")
public class PagingData {

    @Schema(description = "页码", defaultValue = "1")
    private long current = 1;

    @Schema(description = "页容量", defaultValue = "10")
    private long size = 10;

    @Valid
    @Schema(description = "排序集")
    private List<OrderEntry> orders = Lists.newArrayList();

    public static PagingData noPaging() {
        PagingData result = new PagingData();
        result.setCurrent(1);
        result.setSize(-1);
        return result;
    }
}