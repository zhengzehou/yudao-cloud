package cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会话（群组） Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoomGroupRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16653")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "会话创建人", requiredMode = Schema.RequiredMode.REQUIRED, example = "9737")
    @ExcelProperty("会话创建人")
    private Long createUserId;

    @Schema(description = "会话名称", example = "王五")
    @ExcelProperty("会话名称")
    private String roomName;

    @Schema(description = "渠道，如商城、福利、掌柜、小店等业务线")
    @ExcelProperty("渠道，如商城、福利、掌柜、小店等业务线")
    private String channel;

    @Schema(description = "来源，如商品、订单、活动、客服中心、反馈中心等入口")
    @ExcelProperty("来源，如商品、订单、活动、客服中心、反馈中心等入口")
    private String source;

    @Schema(description = "终端，如PC、手机、小程序、公众号、APP等")
    @ExcelProperty("终端，如PC、手机、小程序、公众号、APP等")
    private String term;

    @Schema(description = "ios、android、windows、macos", example = "1")
    @ExcelProperty("ios、android、windows、macos")
    private String osType;

    @Schema(description = "商品ID", example = "12853")
    @ExcelProperty("商品ID")
    private String itemId;

    @Schema(description = "店铺ID", example = "21534")
    @ExcelProperty("店铺ID")
    private String shopId;

    @Schema(description = "订单ID", example = "26678")
    @ExcelProperty("订单ID")
    private String orderId;

    @Schema(description = "商家ID", example = "11046")
    @ExcelProperty("商家ID")
    private String merId;

    @Schema(description = "会话评分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("会话评分")
    private Integer rate;

    @Schema(description = "会话类型（0 好友、1多人、2群组）", example = "2")
    @ExcelProperty("会话类型（0 好友、1多人、2群组）")
    private Integer type;

    @Schema(description = "当前群主", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前群主")
    private Long owner;

    @Schema(description = "当前状态（0正常、1解散）", example = "2")
    @ExcelProperty("当前状态（0正常、1解散）")
    private Integer status;

    @Schema(description = "最新消息ID", example = "13292")
    @ExcelProperty("最新消息ID")
    private Long lastedMsgId;

    @Schema(description = "最新消息内容")
    @ExcelProperty("最新消息内容")
    private String lastedMsg;

    @Schema(description = "最新消息时间")
    @ExcelProperty("最新消息时间")
    private LocalDateTime lastedMsgTime;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发送时间")
    private LocalDateTime createTime;

}