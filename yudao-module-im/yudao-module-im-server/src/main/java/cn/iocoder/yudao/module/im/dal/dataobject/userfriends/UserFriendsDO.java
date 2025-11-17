package cn.iocoder.yudao.module.im.dal.dataobject.userfriends;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 我的好友 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_friends")
@KeySequence("im_user_friends_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendsDO extends BaseDO {

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
     * 好友来源（0主动添加，1对方申请，2朋友推荐）
     */
    private Integer friendSource;
    /**
     * 推荐人ID
     */
    private Long recUserId;
    /**
     * 当前状态（0通过、1待通过、2拒绝、4删除、7拉黑、3临时禁用）
     */
    private Integer status;

    /**
     * 添加时的备注
     */
    private String memo;


}