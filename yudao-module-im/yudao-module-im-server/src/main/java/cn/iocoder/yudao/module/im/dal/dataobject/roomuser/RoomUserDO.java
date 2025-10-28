package cn.iocoder.yudao.module.im.dal.dataobject.roomuser;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会话人员和会话设置 DO
 *
 * @author 芋道源码
 */
@TableName("im_room_user")
@KeySequence("im_room_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomUserDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 会话ID
     */
    private Long roomId;
    /**
     * 会话名称
     */
    private String roomGroup;
    /**
     * 会话备注
     */
    private String roomRemark;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 人员进入会话 类型（1 主动发起，2邀请）
     */
    private Integer entryType;
    /**
     * 邀请人
     */
    private Long inviter;
    /**
     * 已读最大消息ID
     */
    private Long readMsgId;
    /**
     * 会话中的昵称
     */
    private String nickName;
    /**
     * 群聊备注
     */
    private String remark;
    /**
     * 人员状态（1 剔除会话、2主动退出会话）
     */
    private Integer userStatus;
    /**
     * 消息免打扰
     */
    private Integer noDisturbing;
    /**
     * 隐藏会话（会话列表不显示，不影响收消息）
     */
    private Integer hide;
    /**
     * 删除会话（作废当前会话，有消息进来重新生成新的回话，并不会展示已删除会话的消息）
     */
    private Integer delete;
    /**
     * 置顶
     */
    private Integer stick;
    /**
     * 置顶时间
     */
    private LocalDateTime stickTime;
    /**
     * 聊天背景图
     */
    private String bgPic;
    /**
     * 禁用开始时间，临时拉黑某人的会话
     */
    private LocalDateTime forbidStartTime;
    /**
     * 禁用结束时间
     */
    private LocalDateTime forbidEndTime;
    /**
     * 声音提示
     */
    private Integer alert;
    /**
     * 提示音
     */
    private String alertBell;
    /**
     * 来电铃声
     */
    private String callBell;
    /**
     * 投诉理由
     */
    private String complaint;


}