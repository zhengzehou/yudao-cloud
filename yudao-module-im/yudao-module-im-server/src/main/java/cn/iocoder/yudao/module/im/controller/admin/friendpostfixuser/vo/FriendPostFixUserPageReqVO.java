package cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 朋友圈特定人员分页 Request VO")
@Data
public class FriendPostFixUserPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "3607")
    private String userId;

    @Schema(description = "朋友圈动态ID", example = "8643")
    private Long postId;

    @Schema(description = "(1提醒 2 可以看 3 禁止看 4 我不喜欢)", example = "2")
    private Integer type;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}