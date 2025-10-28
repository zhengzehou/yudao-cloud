package cn.iocoder.yudao.module.im.controller.admin.userfriends.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 我的好友分页 Request VO")
@Data
public class UserFriendsPageReqVO extends PageParam {

    @Schema(description = "我的账号ID", example = "27657")
    private Long userId;

    @Schema(description = "好友账号ID", example = "13260")
    private Long friendId;

    @Schema(description = "好友来源（0主动添加，1对方申请，2朋友推荐）")
    private Integer friendSource;

    @Schema(description = "推荐人ID", example = "10459")
    private Long recUserId;

    @Schema(description = "当前状态（0通过、1待通过、2拒绝、4删除、7拉黑、3临时禁用）", example = "2")
    private Integer status;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}