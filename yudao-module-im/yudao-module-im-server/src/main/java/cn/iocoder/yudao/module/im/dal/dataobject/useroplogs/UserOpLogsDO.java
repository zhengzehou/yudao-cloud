package cn.iocoder.yudao.module.im.dal.dataobject.useroplogs;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 操作记录明细 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_op_logs")
@KeySequence("im_user_op_logs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOpLogsDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 操作类型 (修改昵称，修改签名，修改基本信息，删除会话记录，关注、收藏、点赞、会话群主变更、会话设置、更换手机号、更换邮箱)
     */
    private Integer opType;
    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 事件名称
     */
    private String event;
    /**
     * 备注
     */
    private String remark;


}