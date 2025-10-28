package cn.iocoder.yudao.module.im.controller.app.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RoomReadInfo {
    private Long roomId;
    private Long readMsgId;
}
