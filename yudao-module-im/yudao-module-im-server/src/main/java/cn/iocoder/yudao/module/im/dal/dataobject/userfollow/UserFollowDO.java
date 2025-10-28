package cn.iocoder.yudao.module.im.dal.dataobject.userfollow;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 我的关注 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_follow")
@KeySequence("im_user_follow_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowDO extends BaseDO {

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
     * 关注的好友ID
     */
    private Long followUserId;
    /**
     * 0 关注 1 取消关注
     */
    private Integer status;


}