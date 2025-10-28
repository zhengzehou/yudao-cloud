package cn.iocoder.yudao.module.im.dal.dataobject.postcomment;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 朋友圈评论列 DO
 *
 * @author 芋道源码
 */
@TableName("im_post_comment")
@KeySequence("im_post_comment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDO extends BaseDO {

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
     * 评论内容
     */
    private String content;
    /**
     * 上级评论内容的ID
     */
    private Long upperId;
    /**
     * 1删除 2 取消 0 默认
     */
    private Integer status;


}