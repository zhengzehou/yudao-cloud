package cn.iocoder.yudao.module.im.controller.admin.userlabel.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 人员分组标签分页 Request VO")
@Data
public class UserLabelPageReqVO extends PageParam {

    @Schema(description = "账号用户ID", example = "8030")
    private Long userId;

    @Schema(description = "标签ID", example = "19609")
    private Long tagId;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}