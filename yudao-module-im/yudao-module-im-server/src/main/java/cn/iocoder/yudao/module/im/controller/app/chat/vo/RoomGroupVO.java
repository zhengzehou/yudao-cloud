package cn.iocoder.yudao.module.im.controller.app.chat.vo;

import lombok.Data;

@Data
public class RoomGroupVO {
    private Long id;
    private Long roomId;
    private String name;//roomName
    private String avatar;
    private Integer deleteStatus;
    private String updateTime;
    private String createTime;
    private String detail;
    private Integer role;
    private String joinTime;
    private String memberUpdateTime;
}
