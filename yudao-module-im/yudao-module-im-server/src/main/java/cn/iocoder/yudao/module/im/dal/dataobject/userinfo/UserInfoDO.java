package cn.iocoder.yudao.module.im.dal.dataobject.userinfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户信息 DO
 *
 * @author 芋道源码
 */
@TableName("im_user_info")
@KeySequence("im_user_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 账号编码
     */
    private String username;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 名称
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 首字母
     */
    private String firstChar;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别 （0保密、1男、2女）
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 出生日期
     */
    private LocalDate birthday;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 注册IP
     */
    private String registerIp;

    /*
      *  账号注册类型", example = "2 密码 0手机，1邮箱"
     */
    private Integer registerType;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 地区
     */
    private String area;
    /**
     * 默认铃声
     */
    private String defBell;
    /**
     * 账号创建方式(1邀请、0注册)
     */
    private Integer accSource;

    /**
     * 注册设备
     */
    private Integer term;
    /**
     * 账号状态 0正常 1 停用
     */
    private Integer status;


}