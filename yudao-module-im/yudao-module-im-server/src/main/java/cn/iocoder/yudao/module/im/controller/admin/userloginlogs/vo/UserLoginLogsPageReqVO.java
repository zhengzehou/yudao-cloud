package cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 登录历史分页 Request VO")
@Data
public class UserLoginLogsPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "10370")
    private Long userId;

    @Schema(description = "登录方式 1 账号 2手机 3邮箱", example = "2")
    private Integer loginType;

    @Schema(description = "登录终端")
    private String loginTerm;

    @Schema(description = "登录国家")
    private String country;

    @Schema(description = "登录省名称")
    private String province;

    @Schema(description = "登录城市名称")
    private String city;

    @Schema(description = "登录区县名称")
    private String county;

    @Schema(description = "登录的具体地址")
    private String address;

    @Schema(description = "登录的系统信息")
    private String sysInfo;

    @Schema(description = "浏览器信息")
    private String browser;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}