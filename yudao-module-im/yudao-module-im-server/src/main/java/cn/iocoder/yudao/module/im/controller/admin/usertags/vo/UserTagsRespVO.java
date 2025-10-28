package cn.iocoder.yudao.module.im.controller.admin.usertags.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 用户标签库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserTagsRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23699")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22294")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("标签名称")
    private String tagName;

    @Schema(description = "标签描述")
    @ExcelProperty("标签描述")
    private String tagDesc;

    @Schema(description = "展示顺序")
    @ExcelProperty("展示顺序")
    private Integer sort;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}