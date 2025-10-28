package cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 操作记录明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserOpLogsRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27620")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2376")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "操作类型 (修改昵称，修改签名，修改基本信息，删除会话记录，关注、收藏、点赞、会话群主变更、会话设置、更换手机号、更换邮箱)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("操作类型 (修改昵称，修改签名，修改基本信息，删除会话记录，关注、收藏、点赞、会话群主变更、会话设置、更换手机号、更换邮箱)")
    private Integer opType;

    @Schema(description = "业务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8677")
    @ExcelProperty("业务ID")
    private String bizId;

    @Schema(description = "事件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件名称")
    private String event;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}