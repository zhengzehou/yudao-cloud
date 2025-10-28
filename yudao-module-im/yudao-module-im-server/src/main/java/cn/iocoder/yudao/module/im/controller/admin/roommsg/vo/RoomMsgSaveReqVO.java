package cn.iocoder.yudao.module.im.controller.admin.roommsg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 会话消息新增/修改 Request VO")
@Data
public class RoomMsgSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8992")
    private Long id;

    @Schema(description = "会话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13382")
    @NotNull(message = "会话ID不能为空")
    private Long roomId;

    @Schema(description = "发送人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16783")
    @NotNull(message = "发送人ID不能为空")
    private Long sendUserId;

    @Schema(description = "消息类型（0文本、1图片、2文件、3语音、4视频、5复杂类型）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "消息类型（0文本、1图片、2文件、3语音、4视频、5复杂类型）不能为空")
    private Integer msgType;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "消息内容不能为空")
    private String msgContent;

    @Schema(description = "消息发送终端")
    private String term;

    @Schema(description = "消息状态0 正常 1撤回 2 删除", example = "2")
    private Integer status;

    @Schema(description = "引用消息ID", example = "28123")
    private Long refMsgId;

    @Schema(description = "0 用户消息 1系统提示、2推荐类型的消息 3 AI消息 4 AI推荐消息 5 知识库消息", example = "1")
    private Integer sysMsgType;

    @Schema(description = "知识库消息ID", example = "13138")
    private Long knowledgeId;

    @Schema(description = "交互类型消息的交互结果")
    private String results;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}