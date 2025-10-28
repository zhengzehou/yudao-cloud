package cn.iocoder.yudao.module.im.controller.admin.postcomment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 朋友圈评论列新增/修改 Request VO")
@Data
public class PostCommentSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20477")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8641")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "动态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26269")
    @NotNull(message = "动态ID不能为空")
    private Long postId;

    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评论内容不能为空")
    private String content;

    @Schema(description = "上级评论内容的ID", example = "13906")
    private Long upperId;

    @Schema(description = "1删除 2 取消 0 默认", example = "2")
    private Integer status;

}