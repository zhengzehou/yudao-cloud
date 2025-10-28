package cn.iocoder.yudao.module.im.controller.admin.roomuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会话人员和会话设置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoomUserRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31262")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "会话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15599")
    @ExcelProperty("会话ID")
    private Long roomId;

    @Schema(description = "会话名称", example = "李四")
    @ExcelProperty("会话名称")
    private String roomName;

    @Schema(description = "会话备注", example = "你说的对")
    @ExcelProperty("会话备注")
    private String roomRemark;

    @Schema(description = "用户ID", example = "10845")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "人员进入会话 类型（1 主动发起，2邀请）", example = "1")
    @ExcelProperty("人员进入会话 类型（1 主动发起，2邀请）")
    private Integer entryType;

    @Schema(description = "邀请人")
    @ExcelProperty("邀请人")
    private Long inviter;

    @Schema(description = "已读最大消息ID", example = "943")
    @ExcelProperty("已读最大消息ID")
    private Long readMsgId;

    @Schema(description = "会话中的昵称", example = "王五")
    @ExcelProperty("会话中的昵称")
    private String nickName;

    @Schema(description = "群聊备注", example = "随便")
    @ExcelProperty("群聊备注")
    private String remark;

    @Schema(description = "人员状态（1 剔除会话、2主动退出会话）", example = "2")
    @ExcelProperty("人员状态（1 剔除会话、2主动退出会话）")
    private Integer userStatus;

    @Schema(description = "消息免打扰")
    @ExcelProperty("消息免打扰")
    private Integer noDisturbing;

    @Schema(description = "隐藏会话（会话列表不显示，不影响收消息）")
    @ExcelProperty("隐藏会话（会话列表不显示，不影响收消息）")
    private Integer hide;

    @Schema(description = "删除会话（作废当前会话，有消息进来重新生成新的回话，并不会展示已删除会话的消息）")
    @ExcelProperty("删除会话（作废当前会话，有消息进来重新生成新的回话，并不会展示已删除会话的消息）")
    private Integer delete;

    @Schema(description = "置顶")
    @ExcelProperty("置顶")
    private Integer stick;

    @Schema(description = "置顶时间")
    @ExcelProperty("置顶时间")
    private LocalDateTime stickTime;

    @Schema(description = "聊天背景图")
    @ExcelProperty("聊天背景图")
    private String bgPic;

    @Schema(description = "禁用开始时间，临时拉黑某人的会话")
    @ExcelProperty("禁用开始时间，临时拉黑某人的会话")
    private LocalDateTime forbidStartTime;

    @Schema(description = "禁用结束时间")
    @ExcelProperty("禁用结束时间")
    private LocalDateTime forbidEndTime;

    @Schema(description = "声音提示")
    @ExcelProperty("声音提示")
    private Integer alert;

    @Schema(description = "提示音")
    @ExcelProperty("提示音")
    private String alertBell;

    @Schema(description = "来电铃声")
    @ExcelProperty("来电铃声")
    private String callBell;

    @Schema(description = "投诉理由")
    @ExcelProperty("投诉理由")
    private String complaint;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}