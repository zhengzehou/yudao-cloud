package cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 操作记录明细新增/修改 Request VO")
@Data
public class UserOpLogsSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27620")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2376")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "操作类型 (修改昵称，修改签名，修改基本信息，删除会话记录，关注、收藏、点赞、会话群主变更、会话设置、更换手机号、更换邮箱)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "操作类型 (修改昵称，修改签名，修改基本信息，删除会话记录，关注、收藏、点赞、会话群主变更、会话设置、更换手机号、更换邮箱)不能为空")
    private Integer opType;

    @Schema(description = "业务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8677")
    @NotEmpty(message = "业务ID不能为空")
    private String bizId;

    @Schema(description = "事件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件名称不能为空")
    private String event;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}