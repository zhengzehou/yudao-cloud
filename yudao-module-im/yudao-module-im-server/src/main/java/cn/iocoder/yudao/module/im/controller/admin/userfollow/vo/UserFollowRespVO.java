package cn.iocoder.yudao.module.im.controller.admin.userfollow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 我的关注 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserFollowRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19807")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32718")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "关注的好友ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18155")
    @ExcelProperty("关注的好友ID")
    private Long followUserId;

    @Schema(description = "0 关注 1 取消关注", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("0 关注 1 取消关注")
    private Integer status;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}