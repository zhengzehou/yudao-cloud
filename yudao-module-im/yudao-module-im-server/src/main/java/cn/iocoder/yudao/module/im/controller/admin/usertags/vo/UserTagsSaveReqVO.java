package cn.iocoder.yudao.module.im.controller.admin.usertags.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 用户标签库新增/修改 Request VO")
@Data
public class UserTagsSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23699")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22294")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "标签名称不能为空")
    private String tagName;

    @Schema(description = "标签描述")
    private String tagDesc;

    @Schema(description = "展示顺序")
    private Integer sort;

}