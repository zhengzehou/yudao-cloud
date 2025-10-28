package cn.iocoder.yudao.module.im.controller.admin.postlike.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 朋友圈点赞和踩 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PostLikeRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1511")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3403")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "动态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2601")
    @ExcelProperty("动态ID")
    private Long postId;

    @Schema(description = "0 点赞  1 不喜欢", example = "2")
    @ExcelProperty("0 点赞  1 不喜欢")
    private Integer type;

    @Schema(description = "0默认 1点赞 2取消点赞", example = "2")
    @ExcelProperty("0默认 1点赞 2取消点赞")
    private Integer status;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}