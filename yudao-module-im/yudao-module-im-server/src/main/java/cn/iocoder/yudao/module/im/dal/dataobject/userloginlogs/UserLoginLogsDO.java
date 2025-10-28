package cn.iocoder.yudao.module.im.dal.dataobject.userloginlogs;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 登录历史 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_login_logs")
@KeySequence("im_user_login_logs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginLogsDO extends BaseDO {

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
     * 登录方式 1 账号 2手机 3邮箱
     */
    private Integer loginType;
    /**
     * 登录终端
     */
    private String loginTerm;
    /**
     * 登录国家
     */
    private String country;
    /**
     * 登录省名称
     */
    private String province;
    /**
     * 登录城市名称
     */
    private String city;
    /**
     * 登录区县名称
     */
    private String county;
    /**
     * 登录的具体地址
     */
    private String address;
    /**
     * 登录的系统信息
     */
    private String sysInfo;
    /**
     * 浏览器信息
     */
    private String browser;
    /**
     * 备注
     */
    private String remark;


}