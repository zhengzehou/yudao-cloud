package cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 朋友圈特定人员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FriendPostFixUserRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30612")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3607")
    @ExcelProperty("用户ID")
    private String userId;

    @Schema(description = "朋友圈动态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8643")
    @ExcelProperty("朋友圈动态ID")
    private Long postId;

    @Schema(description = "(1提醒 2 可以看 3 禁止看 4 我不喜欢)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("(1提醒 2 可以看 3 禁止看 4 我不喜欢)")
    private Integer type;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}