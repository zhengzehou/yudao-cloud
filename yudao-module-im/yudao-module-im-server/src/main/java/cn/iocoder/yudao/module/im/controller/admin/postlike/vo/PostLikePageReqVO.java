package cn.iocoder.yudao.module.im.controller.admin.postlike.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 朋友圈点赞和踩分页 Request VO")
@Data
public class PostLikePageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "3403")
    private Long userId;

    @Schema(description = "动态ID", example = "2601")
    private Long postId;

    @Schema(description = "0 点赞  1 不喜欢", example = "2")
    private Integer type;

    @Schema(description = "0默认 1点赞 2取消点赞", example = "2")
    private Integer status;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}