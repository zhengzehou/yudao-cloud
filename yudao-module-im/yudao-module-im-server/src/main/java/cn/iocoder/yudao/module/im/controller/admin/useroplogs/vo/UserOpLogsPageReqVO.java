package cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 操作记录明细分页 Request VO")
@Data
public class UserOpLogsPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "2376")
    private Long userId;

    @Schema(description = "操作类型 (修改昵称，修改签名，修改基本信息，删除会话记录，关注、收藏、点赞、会话群主变更、会话设置、更换手机号、更换邮箱)", example = "1")
    private Integer opType;

    @Schema(description = "业务ID", example = "8677")
    private String bizId;

    @Schema(description = "事件名称")
    private String event;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}