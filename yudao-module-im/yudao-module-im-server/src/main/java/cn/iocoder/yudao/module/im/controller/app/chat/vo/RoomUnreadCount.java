package cn.iocoder.yudao.module.im.controller.app.chat.vo;

import lombok.Data;

@Data
public class RoomUnreadCount {
    private Long roomId;
    private Integer unreadCount;
}
