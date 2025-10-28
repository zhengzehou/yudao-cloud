package cn.iocoder.yudao.module.im.controller.admin.postlike.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 朋友圈点赞和踩新增/修改 Request VO")
@Data
public class PostLikeSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1511")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3403")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "动态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2601")
    @NotNull(message = "动态ID不能为空")
    private Long postId;

    @Schema(description = "0 点赞  1 不喜欢", example = "2")
    private Integer type;

    @Schema(description = "0默认 1点赞 2取消点赞", example = "2")
    private Integer status;

}