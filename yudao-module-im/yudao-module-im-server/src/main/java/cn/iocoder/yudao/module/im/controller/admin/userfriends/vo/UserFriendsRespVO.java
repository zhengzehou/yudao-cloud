package cn.iocoder.yudao.module.im.controller.admin.userfriends.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 我的好友 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserFriendsRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12477")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "我的账号ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27657")
    @ExcelProperty("我的账号ID")
    private Long userId;

    @Schema(description = "好友账号ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13260")
    @ExcelProperty("好友账号ID")
    private Long friendId;

    @Schema(description = "好友来源（0主动添加，1对方申请，2朋友推荐）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("好友来源（0主动添加，1对方申请，2朋友推荐）")
    private Integer friendSource;

    @Schema(description = "推荐人ID", example = "10459")
    @ExcelProperty("推荐人ID")
    private Long recUserId;

    @Schema(description = "当前状态（0通过、1待通过、2拒绝、4删除、7拉黑、3临时禁用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("当前状态（0通过、1待通过、2拒绝、4删除、7拉黑、3临时禁用）")
    private Integer status;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}