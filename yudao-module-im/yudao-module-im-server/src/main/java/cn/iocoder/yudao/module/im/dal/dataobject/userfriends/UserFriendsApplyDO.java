package cn.iocoder.yudao.module.im.dal.dataobject.userfriends;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 我的好友 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_friends_apply")
@KeySequence("im_user_friendsAdd 'value='_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendsApplyDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 我的账号ID
     */
    private Long userId;
    /**
     * 好友账号ID
     */
    private Long friendId;

    /**
     * 当前状态（1通过、0待通过、2拒绝）
     */
    private Integer status;

    /**
     * 添加时的备注
     */
    private String msg;


}