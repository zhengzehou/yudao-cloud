package cn.iocoder.yudao.module.im.controller.admin.userfollow.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 我的关注分页 Request VO")
@Data
public class UserFollowPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "32718")
    private Long userId;

    @Schema(description = "关注的好友ID", example = "18155")
    private Long followUserId;

    @Schema(description = "0 关注 1 取消关注", example = "2")
    private Integer status;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}