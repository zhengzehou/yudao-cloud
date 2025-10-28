package cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 历史头像新增/修改 Request VO")
@Data
public class UsedAvatarSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27845")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9441")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "头像URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotEmpty(message = "头像URL不能为空")
    private String avatarUrl;

}