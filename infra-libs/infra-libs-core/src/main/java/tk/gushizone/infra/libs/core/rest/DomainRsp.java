package tk.gushizone.infra.libs.core.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 领域响应
 *
 * @author zhangwei
 * @since 2024/9/21
 */
@Getter
@Setter
@Accessors(chain = true)
public class DomainRsp {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "创建人")
    private Long createdBy;

    @Schema(description = "创建时间")
    private Date createdAt;

    @Schema(description = "更新人")
    private Long updatedBy;

    @Schema(description = "更新时间")
    private Date updatedAt;

    @Schema(description = "版本号")
    private Integer version;

}
