package cn.iocoder.yudao.module.im.controller.admin.userfollow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 我的关注新增/修改 Request VO")
@Data
public class UserFollowSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19807")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32718")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "关注的好友ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18155")
    @NotNull(message = "关注的好友ID不能为空")
    private Long followUserId;

    @Schema(description = "0 关注 1 取消关注", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "0 关注 1 取消关注不能为空")
    private Integer status;

}