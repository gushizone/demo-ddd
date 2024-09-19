package tk.gushizone.infra.libs.core.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;

/**
 * @author gushizone
 * @since 2024/9/16
 */
@Data
@Schema(name = "RevisionRecordReq", description = "标准的版本记录")
public class RevisionRecordReq implements RevisionRecord {

    @NotNull
    @Schema(description = "主键")
    private Long id;

    @NotNull
    @Schema(description = "版本号")
    private Integer revision;
}
