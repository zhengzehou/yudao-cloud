package cn.iocoder.yudao.module.im.dal.dataobject.friendpostfixuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 朋友圈特定人员 DO
 *
 * @author 芋道源码
 */
@TableName("im_friend_post_fix_user")
@KeySequence("im_friend_post_fix_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendPostFixUserDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 朋友圈动态ID
     */
    private Long postId;
    /**
     * (1提醒 2 可以看 3 禁止看 4 我不喜欢)
     */
    private Integer type;


}