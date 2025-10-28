package cn.iocoder.yudao.module.im.controller.admin.friendpost.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 朋友圈动态 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FriendPostRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15277")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31091")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "动态标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("动态标题")
    private String title;

    @Schema(description = "动态内容")
    @ExcelProperty("动态内容")
    private String content;

    @Schema(description = "(1发布、0草稿)", example = "1")
    @ExcelProperty("(1发布、0草稿)")
    private Integer status;

    @Schema(description = "发布时所在国家")
    @ExcelProperty("发布时所在国家")
    private String country;

    @Schema(description = "发布时所在省份")
    @ExcelProperty("发布时所在省份")
    private String province;

    @Schema(description = "发布时所在城市")
    @ExcelProperty("发布时所在城市")
    private String city;

    @Schema(description = "发布时所在区县")
    @ExcelProperty("发布时所在区县")
    private String county;

    @Schema(description = "发布时所在具体地址")
    @ExcelProperty("发布时所在具体地址")
    private String address;

    @Schema(description = "发布年")
    @ExcelProperty("发布年")
    private Integer year;

    @Schema(description = "发布月")
    @ExcelProperty("发布月")
    private Integer month;

    @Schema(description = "发布日")
    @ExcelProperty("发布日")
    private Integer day;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime pubTime;

    @Schema(description = "谁可以看（公开、私密、部分可见，不给谁看）")
    @ExcelProperty("谁可以看（公开、私密、部分可见，不给谁看）")
    private Integer showTo;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}