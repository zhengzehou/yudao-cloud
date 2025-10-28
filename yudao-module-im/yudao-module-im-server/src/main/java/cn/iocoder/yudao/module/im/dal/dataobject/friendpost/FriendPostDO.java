package cn.iocoder.yudao.module.im.dal.dataobject.friendpost;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 朋友圈动态 DO
 *
 * @author 芋道源码
 */
@TableName("im_friend_post")
@KeySequence("im_friend_post_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendPostDO extends BaseDO {

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
     * 动态标题
     */
    private String title;
    /**
     * 动态内容
     */
    private String content;
    /**
     * (1发布、0草稿)
     */
    private Integer status;
    /**
     * 发布时所在国家
     */
    private String country;
    /**
     * 发布时所在省份
     */
    private String province;
    /**
     * 发布时所在城市
     */
    private String city;
    /**
     * 发布时所在区县
     */
    private String county;
    /**
     * 发布时所在具体地址
     */
    private String address;
    /**
     * 发布年
     */
    private Integer year;
    /**
     * 发布月
     */
    private Integer month;
    /**
     * 发布日
     */
    private Integer day;
    /**
     * 发布时间
     */
    private LocalDateTime pubTime;
    /**
     * 谁可以看（公开、私密、部分可见，不给谁看）
     */
    private Integer showTo;


}