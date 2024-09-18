package tk.gushizone.infra.libs.core.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tk.gushizone.infra.libs.base.entity.RevisionRecord;

/**
 * @author gushizone
 * @since 2024/9/16
 */
@Data
@Schema(name = "RevisionRecordReq", description = "标准的版本记录")
public class RevisionRecordReq implements RevisionRecord {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "版本号")
    private Integer revision;
}
