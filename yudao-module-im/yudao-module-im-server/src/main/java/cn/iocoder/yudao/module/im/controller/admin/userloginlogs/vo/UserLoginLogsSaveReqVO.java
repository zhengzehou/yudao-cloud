package cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 登录历史新增/修改 Request VO")
@Data
public class UserLoginLogsSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23872")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10370")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "登录方式 1 账号 2手机 3邮箱", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "登录方式 1 账号 2手机 3邮箱不能为空")
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

}