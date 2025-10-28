package cn.iocoder.yudao.module.im.controller.admin.postcomment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 朋友圈评论列 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PostCommentRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20477")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8641")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "动态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26269")
    @ExcelProperty("动态ID")
    private Long postId;

    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评论内容")
    private String content;

    @Schema(description = "上级评论内容的ID", example = "13906")
    @ExcelProperty("上级评论内容的ID")
    private Long upperId;

    @Schema(description = "1删除 2 取消 0 默认", example = "2")
    @ExcelProperty("1删除 2 取消 0 默认")
    private Integer status;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}