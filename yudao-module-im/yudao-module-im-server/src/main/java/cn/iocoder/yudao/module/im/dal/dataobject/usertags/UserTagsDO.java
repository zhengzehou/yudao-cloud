package cn.iocoder.yudao.module.im.dal.dataobject.usertags;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户标签库 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_tags")
@KeySequence("im_user_tags_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTagsDO extends BaseDO {

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
     * 标签名称
     */
    private String tagName;
    /**
     * 标签描述
     */
    private String tagDesc;
    /**
     * 展示顺序
     */
    private Integer sort;


}