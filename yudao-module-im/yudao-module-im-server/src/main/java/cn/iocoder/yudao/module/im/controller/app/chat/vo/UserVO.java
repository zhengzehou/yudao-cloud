package cn.iocoder.yudao.module.im.controller.app.chat.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long userId;
    private String avatar;
    private String nickName;
    private Integer type;
    private Integer activeStatus;
}
