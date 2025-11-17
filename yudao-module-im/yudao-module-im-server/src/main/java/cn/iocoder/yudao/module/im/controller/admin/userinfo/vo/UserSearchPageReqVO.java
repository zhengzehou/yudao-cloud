package cn.iocoder.yudao.module.im.controller.admin.userinfo.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Data
public class UserSearchPageReqVO extends PageParam {

    @Schema(description = "账号ID")
    private Long userId;

    @Schema(description = "账号编码")
    private String keyword;

}