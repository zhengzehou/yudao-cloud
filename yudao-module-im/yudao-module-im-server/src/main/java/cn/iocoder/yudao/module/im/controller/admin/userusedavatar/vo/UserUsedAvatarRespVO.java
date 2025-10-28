package cn.iocoder.yudao.module.im.controller.admin.userusedavatar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 历史头像 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserUsedAvatarRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17795")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6896")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "头像URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("头像URL")
    private String avatarUrl;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}