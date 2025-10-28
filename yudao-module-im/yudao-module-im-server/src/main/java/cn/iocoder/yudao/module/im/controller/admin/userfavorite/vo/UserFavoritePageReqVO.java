package cn.iocoder.yudao.module.im.controller.admin.userfavorite.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 我的收藏分页 Request VO")
@Data
public class UserFavoritePageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "3955")
    private Long userId;

    @Schema(description = "类型（1图片、0消息、2发票、3URL）", example = "1")
    private Integer type;

    @Schema(description = "发送人ID", example = "14598")
    private Long sendUserId;

    @Schema(description = "收藏的消息ID", example = "10061")
    private Long msgId;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}