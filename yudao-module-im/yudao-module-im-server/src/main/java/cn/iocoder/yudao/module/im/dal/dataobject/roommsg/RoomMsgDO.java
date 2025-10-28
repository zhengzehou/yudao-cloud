package cn.iocoder.yudao.module.im.dal.dataobject.roommsg;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会话消息 DO
 *
 * @author 芋道源码
 */
@TableName("im_room_msg")
@KeySequence("im_room_msg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomMsgDO extends BaseDO {

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
     * 发送人ID
     */
    private Long sendUserId;
    /**
     * 消息类型（0文本、1图片、2文件、3语音、4视频、5复杂类型）
     */
    private Integer msgType;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 消息发送终端
     */
    private String term;
    /**
     * 消息状态0 正常 1撤回 2 删除
     */
    private Integer status;
    /**
     * 引用消息ID
     */
    private Long refMsgId;
    /**
     * 0 用户消息 1系统提示、2推荐类型的消息 3 AI消息 4 AI推荐消息 5 知识库消息
     */
    private Integer sysMsgType;
    /**
     * 知识库消息ID
     */
    private Long knowledgeId;
    /**
     * 交互类型消息的交互结果
     */
    private String results;
    /**
     * 备注
     */
    private String remark;


}