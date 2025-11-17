package cn.iocoder.yudao.module.im.controller.admin.userfriends.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 我的好友 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserFriendsApplyRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25907")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "账号", example = "张三")
    @ExcelProperty("账号")
    private String username;
    @Schema(description = "昵称", example = "张三")
    @ExcelProperty("昵称")
    private String nickName;

    @Schema(description = "头像")
    @ExcelProperty("头像")
    private String avatar;

    @Schema(description = "性别 （0保密、1男、2女）")
    @ExcelProperty("性别 （0保密、1男、2女）")
    private Integer gender;

    @Schema(description = "年龄")
    @ExcelProperty("年龄")
    private Integer age;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDate birthday;

    @Schema(description = "个性签名")
    @ExcelProperty("个性签名")
    private String slogan;

    @Schema(description = "地区")
    @ExcelProperty("地区")
    private String area;

    @Schema(description = "我的账号ID")
    @ExcelProperty("我的账号ID")
    private Long userId;

    @Schema(description = "好友账号ID")
    @ExcelProperty("好友账号ID")
    private Long friendId;

    @Schema(description = "添加时的备注")
    @ExcelProperty("添加时的备注")
    private String msg;

    @Schema(description = "留言回复")
    @ExcelProperty("留言回复")
    private String ansMemo;

    @Schema(description = "当前状态（0通过、1待通过、2拒绝）")
    @ExcelProperty("当前状态（0通过、1待通过、2拒绝、）")
    private Integer status;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime createTime;

    private Boolean isLast;

}