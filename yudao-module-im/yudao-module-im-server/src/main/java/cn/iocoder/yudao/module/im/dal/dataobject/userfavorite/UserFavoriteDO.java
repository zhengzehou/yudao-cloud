package cn.iocoder.yudao.module.im.dal.dataobject.userfavorite;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 我的收藏 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_favorite")
@KeySequence("im_user_favorite_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFavoriteDO extends BaseDO {

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
     * 类型（1图片、0消息、2发票、3URL）
     */
    private Integer type;
    /**
     * 发送人ID
     */
    private Long sendUserId;
    /**
     * 收藏的消息ID
     */
    private Long msgId;


}