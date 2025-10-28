package cn.iocoder.yudao.module.im.controller.admin.userfavorite.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 我的收藏 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserFavoriteRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "328")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3955")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "类型（1图片、0消息、2发票、3URL）", example = "1")
    @ExcelProperty("类型（1图片、0消息、2发票、3URL）")
    private Integer type;

    @Schema(description = "发送人ID", example = "14598")
    @ExcelProperty("发送人ID")
    private Long sendUserId;

    @Schema(description = "收藏的消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10061")
    @ExcelProperty("收藏的消息ID")
    private Long msgId;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}