package cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 朋友圈特定人员新增/修改 Request VO")
@Data
public class FriendPostFixUserSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30612")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3607")
    @NotEmpty(message = "用户ID不能为空")
    private String userId;

    @Schema(description = "朋友圈动态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8643")
    @NotNull(message = "朋友圈动态ID不能为空")
    private Long postId;

    @Schema(description = "(1提醒 2 可以看 3 禁止看 4 我不喜欢)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "(1提醒 2 可以看 3 禁止看 4 我不喜欢)不能为空")
    private Integer type;

}