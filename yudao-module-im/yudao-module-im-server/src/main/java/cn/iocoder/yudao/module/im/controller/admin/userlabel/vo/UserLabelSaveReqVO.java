package cn.iocoder.yudao.module.im.controller.admin.userlabel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 人员分组标签新增/修改 Request VO")
@Data
public class UserLabelSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2339")
    private Long id;

    @Schema(description = "账号用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8030")
    @NotNull(message = "账号用户ID不能为空")
    private Long userId;

    @Schema(description = "标签ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19609")
    @NotNull(message = "标签ID不能为空")
    private Long tagId;

}