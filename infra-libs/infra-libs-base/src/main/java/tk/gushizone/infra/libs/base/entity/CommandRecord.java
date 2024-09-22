package tk.gushizone.infra.libs.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 带版本的记录 todo
 * - 删改的基础条件
 *
 * @author gushizone
 * @since 2024/9/16
 */
@Data
@Schema(name = "CommandRecord", description = "标准的版本记录")
public class CommandRecord {

    @NotNull
    @Schema(description = "主键")
    private Long id;

    @NotNull
    @Schema(description = "版本号")
    private Integer version;
}
