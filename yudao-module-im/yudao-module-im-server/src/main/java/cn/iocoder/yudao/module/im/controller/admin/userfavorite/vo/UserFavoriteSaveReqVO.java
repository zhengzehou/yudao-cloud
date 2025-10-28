package cn.iocoder.yudao.module.im.controller.admin.userfavorite.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 我的收藏新增/修改 Request VO")
@Data
public class UserFavoriteSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "328")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3955")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "类型（1图片、0消息、2发票、3URL）", example = "1")
    private Integer type;

    @Schema(description = "发送人ID", example = "14598")
    private Long sendUserId;

    @Schema(description = "收藏的消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10061")
    @NotNull(message = "收藏的消息ID不能为空")
    private Long msgId;

}