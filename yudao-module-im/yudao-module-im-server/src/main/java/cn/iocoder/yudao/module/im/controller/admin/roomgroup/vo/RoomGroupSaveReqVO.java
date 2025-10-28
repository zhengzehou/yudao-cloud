package cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会话（群组）新增/修改 Request VO")
@Data
public class RoomGroupSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16653")
    private Long id;

    @Schema(description = "会话创建人", requiredMode = Schema.RequiredMode.REQUIRED, example = "9737")
    @NotNull(message = "会话创建人不能为空")
    private Long createUserId;

    @Schema(description = "会话名称", example = "王五")
    private String roomName;

    @Schema(description = "渠道，如商城、福利、掌柜、小店等业务线")
    private String channel;

    @Schema(description = "来源，如商品、订单、活动、客服中心、反馈中心等入口")
    private String source;

    @Schema(description = "终端，如PC、手机、小程序、公众号、APP等")
    private String term;

    @Schema(description = "ios、android、windows、macos", example = "1")
    private String osType;

    @Schema(description = "商品ID", example = "12853")
    private String itemId;

    @Schema(description = "店铺ID", example = "21534")
    private String shopId;

    @Schema(description = "订单ID", example = "26678")
    private String orderId;

    @Schema(description = "商家ID", example = "11046")
    private String merId;

    @Schema(description = "会话评分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "会话评分不能为空")
    private Integer rate;

    @Schema(description = "会话类型（0 好友、1多人、2群组）", example = "2")
    private Integer type;

    @Schema(description = "当前群主", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前群主不能为空")
    private Long owner;

    @Schema(description = "当前状态（0正常、1解散）", example = "2")
    private Integer status;

    @Schema(description = "最新消息ID", example = "13292")
    private Long lastedMsgId;

    @Schema(description = "最新消息内容")
    private String lastedMsg;

    @Schema(description = "最新消息时间")
    private LocalDateTime lastedMsgTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}