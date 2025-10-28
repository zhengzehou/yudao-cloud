package cn.iocoder.yudao.module.im.dal.dataobject.postlike;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 朋友圈点赞和踩 DO
 *
 * @author 芋道源码
 */
@TableName("im_post_like")
@KeySequence("im_post_like_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDO extends BaseDO {

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
     * 动态ID
     */
    private Long postId;
    /**
     * 0 点赞  1 不喜欢
     */
    private Integer type;
    /**
     * 0默认 1点赞 2取消点赞
     */
    private Integer status;


}