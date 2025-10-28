package cn.iocoder.yudao.module.im.dal.dataobject.roomgroup;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会话（群组） DO
 *
 * @author 芋道源码
 */
@TableName("im_room_group")
@KeySequence("im_room_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomGroupDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 会话创建人
     */
    private Long createUserId;
    /**
     * 会话名称
     */
    private String roomGroup;
    /**
     * 渠道，如商城、福利、掌柜、小店等业务线
     */
    private String channel;
    /**
     * 来源，如商品、订单、活动、客服中心、反馈中心等入口
     */
    private String source;
    /**
     * 终端，如PC、手机、小程序、公众号、APP等
     */
    private String term;
    /**
     * ios、android、windows、macos
     */
    private String osType;
    /**
     * 商品ID
     */
    private String itemId;
    /**
     * 店铺ID
     */
    private String shopId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 商家ID
     */
    private String merId;
    /**
     * 会话评分
     */
    private Integer rate;
    /**
     * 会话类型（0 好友、1多人、2群组）
     */
    private Integer type;
    /**
     * 当前群主
     */
    private Long owner;
    /**
     * 当前状态（0正常、1解散）
     */
    private Integer status;
    /**
     * 最新消息ID
     */
    private Long lastedMsgId;
    /**
     * 最新消息内容
     */
    private String lastedMsg;
    /**
     * 最新消息时间
     */
    private LocalDateTime lastedMsgTime;
    /**
     * 备注
     */
    private String remark;


}