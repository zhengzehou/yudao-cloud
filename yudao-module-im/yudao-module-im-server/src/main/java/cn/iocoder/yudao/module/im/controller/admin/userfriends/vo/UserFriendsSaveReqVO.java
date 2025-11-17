package cn.iocoder.yudao.module.im.controller.admin.userfriends.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 我的好友新增/修改 Request VO")
@Data
public class UserFriendsSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12477")
    private Long id;

    @Schema(description = "我的账号ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27657")
    @NotNull(message = "我的账号ID不能为空")
    private Long userId;

    @Schema(description = "好友账号ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13260")
    @NotNull(message = "好友账号ID不能为空")
    private Long friendId;

    @Schema(description = "好友来源（0主动添加，1对方申请，2朋友推荐）")
    @NotNull(message = "好友来源（0主动添加，1对方申请，2朋友推荐）不能为空")
    private Integer friendSource;

    @Schema(description = "推荐人ID", example = "10459")
    private Long recUserId;

    @Schema(description = "当前状态（0通过、1待通过、2拒绝、4删除、7拉黑、3临时禁用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "当前状态（0通过、1待通过、2拒绝、4删除、7拉黑、3临时禁用）不能为空")
    private Integer status;

}