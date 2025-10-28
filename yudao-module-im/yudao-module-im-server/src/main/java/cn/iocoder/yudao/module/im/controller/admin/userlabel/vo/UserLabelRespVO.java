package cn.iocoder.yudao.module.im.controller.admin.userlabel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 人员分组标签 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserLabelRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2339")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "账号用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8030")
    @ExcelProperty("账号用户ID")
    private Long userId;

    @Schema(description = "标签ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19609")
    @ExcelProperty("标签ID")
    private Long tagId;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}