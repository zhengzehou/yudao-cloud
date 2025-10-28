package cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 登录历史 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserLoginLogsRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23872")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10370")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "登录方式 1 账号 2手机 3邮箱", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("登录方式 1 账号 2手机 3邮箱")
    private Integer loginType;

    @Schema(description = "登录终端")
    @ExcelProperty("登录终端")
    private String loginTerm;

    @Schema(description = "登录国家")
    @ExcelProperty("登录国家")
    private String country;

    @Schema(description = "登录省名称")
    @ExcelProperty("登录省名称")
    private String province;

    @Schema(description = "登录城市名称")
    @ExcelProperty("登录城市名称")
    private String city;

    @Schema(description = "登录区县名称")
    @ExcelProperty("登录区县名称")
    private String county;

    @Schema(description = "登录的具体地址")
    @ExcelProperty("登录的具体地址")
    private String address;

    @Schema(description = "登录的系统信息")
    @ExcelProperty("登录的系统信息")
    private String sysInfo;

    @Schema(description = "浏览器信息")
    @ExcelProperty("浏览器信息")
    private String browser;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}