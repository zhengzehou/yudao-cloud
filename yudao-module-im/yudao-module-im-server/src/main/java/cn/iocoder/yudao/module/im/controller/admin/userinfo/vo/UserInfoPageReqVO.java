package cn.iocoder.yudao.module.im.controller.admin.userinfo.vo;

import lombok.*;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户信息分页 Request VO")
@Data
public class UserInfoPageReqVO extends PageParam {

    @Schema(description = "账号ID")
    private Long id;

    @Schema(description = "账号编码")
    private String username;

    @Schema(description = "密码")
    private String pwd;

    @Schema(description = "名称", example = "李四")
    private String name;

    @Schema(description = "昵称", example = "张三")
    private String nickName;

    @Schema(description = "首字母")
    private String firstChar;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别 （0保密、1男、2女）")
    private Integer sex;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "出生日期")
    private LocalDate birthday;

    @Schema(description = "个性签名")
    private String sign;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "地区")
    private String area;

    @Schema(description = "默认铃声")
    private String defBell;

    @Schema(description = "账号创建方式(1邀请、0注册)")
    private Integer accSource;

    @Schema(description = "账号状态 0正常 1 停用", example = "1")
    private Integer status;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}