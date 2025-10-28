package cn.iocoder.yudao.module.im.controller.admin.roomuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会话人员和会话设置新增/修改 Request VO")
@Data
public class RoomUserSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31262")
    private Long id;

    @Schema(description = "会话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15599")
    @NotNull(message = "会话ID不能为空")
    private Long roomId;

    @Schema(description = "会话名称", example = "李四")
    private String roomName;

    @Schema(description = "会话备注", example = "你说的对")
    private String roomRemark;

    @Schema(description = "用户ID", example = "10845")
    private Long userId;

    @Schema(description = "人员进入会话 类型（1 主动发起，2邀请）", example = "1")
    private Integer entryType;

    @Schema(description = "邀请人")
    private Long inviter;

    @Schema(description = "已读最大消息ID", example = "943")
    private Long readMsgId;

    @Schema(description = "会话中的昵称", example = "王五")
    private String nickName;

    @Schema(description = "群聊备注", example = "随便")
    private String remark;

    @Schema(description = "人员状态（1 剔除会话、2主动退出会话）", example = "2")
    private Integer userStatus;

    @Schema(description = "消息免打扰")
    private Integer noDisturbing;

    @Schema(description = "隐藏会话（会话列表不显示，不影响收消息）")
    private Integer hide;

    @Schema(description = "删除会话（作废当前会话，有消息进来重新生成新的回话，并不会展示已删除会话的消息）")
    private Integer delete;

    @Schema(description = "置顶")
    private Integer stick;

    @Schema(description = "置顶时间")
    private LocalDateTime stickTime;

    @Schema(description = "聊天背景图")
    private String bgPic;

    @Schema(description = "禁用开始时间，临时拉黑某人的会话")
    private LocalDateTime forbidStartTime;

    @Schema(description = "禁用结束时间")
    private LocalDateTime forbidEndTime;

    @Schema(description = "声音提示")
    private Integer alert;

    @Schema(description = "提示音")
    private String alertBell;

    @Schema(description = "来电铃声")
    private String callBell;

    @Schema(description = "投诉理由")
    private String complaint;

}