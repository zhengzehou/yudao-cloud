package cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 历史头像分页 Request VO")
@Data
public class UsedAvatarPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "9441")
    private Long userId;

    @Schema(description = "头像URL", example = "https://www.iocoder.cn")
    private String avatarUrl;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}