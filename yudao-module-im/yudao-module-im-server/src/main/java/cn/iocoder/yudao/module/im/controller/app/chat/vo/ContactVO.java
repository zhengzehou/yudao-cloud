package cn.iocoder.yudao.module.im.controller.app.chat.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactVO {
    private Long roomId;
    private Integer type;// 暂时理解为用户类型
    private Integer hotFlag;//
    private String text;
    private String name;//昵称
    private String avatar;
    private Long activeTime;
    private Integer unreadCount;
    private String roomGroup;
    private String member;
    private String targetUid;
    private Integer selfExist;
    private LocalDateTime pinTime;
    private Integer noticeStatus;
    private Integer shieldStatus;
    private Long lastMsgId;
}