package cn.iocoder.yudao.module.im.controller.admin.userinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 用户信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserInfoRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25907")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "账号编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账号编码")
    private String username;

//    @Schema(description = "密码")
//    @ExcelProperty("密码")
//    private String pwd;

    @Schema(description = "名称", example = "李四")
    @ExcelProperty("名称")
    private String name;

    @Schema(description = "昵称", example = "张三")
    @ExcelProperty("昵称")
    private String nickName;

    @Schema(description = "首字母")
    @ExcelProperty("首字母")
    private String firstChar;

    @Schema(description = "头像")
    @ExcelProperty("头像")
    private String avatar;

    @Schema(description = "性别 （0保密、1男、2女）")
    @ExcelProperty("性别 （0保密、1男、2女）")
    private Integer sex;

    @Schema(description = "年龄")
    @ExcelProperty("年龄")
    private Integer age;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDate birthday;

    @Schema(description = "个性签名")
    @ExcelProperty("个性签名")
    private String sign;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("邮箱")
    private String email;

    @Schema(description = "地区")
    @ExcelProperty("地区")
    private String area;

    @Schema(description = "默认铃声")
    @ExcelProperty("默认铃声")
    private String defBell;

    @Schema(description = "账号创建方式(1邀请、0注册)")
    @ExcelProperty("账号创建方式(1邀请、0注册)")
    private Integer accSource;

    @Schema(description = "账号状态 0正常 1 停用", example = "1")
    @ExcelProperty("账号状态 0正常 1 停用")
    private Integer status;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}