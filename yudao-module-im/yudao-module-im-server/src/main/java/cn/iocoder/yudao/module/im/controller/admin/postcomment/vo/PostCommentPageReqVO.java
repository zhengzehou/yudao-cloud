package cn.iocoder.yudao.module.im.controller.admin.postcomment.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 朋友圈评论列分页 Request VO")
@Data
public class PostCommentPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "8641")
    private Long userId;

    @Schema(description = "动态ID", example = "26269")
    private Long postId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "上级评论内容的ID", example = "13906")
    private Long upperId;

    @Schema(description = "1删除 2 取消 0 默认", example = "2")
    private Integer status;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}