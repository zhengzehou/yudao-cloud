package cn.iocoder.yudao.module.im.dal.dataobject.userlabel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员分组标签 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_label")
@KeySequence("im_user_label_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLabelDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 账号用户ID
     */
    private Long userId;
    /**
     * 标签ID
     */
    private Long tagId;


}