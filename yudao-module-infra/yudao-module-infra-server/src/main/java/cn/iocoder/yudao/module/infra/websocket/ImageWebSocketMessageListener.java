package cn.iocoder.yudao.module.infra.websocket;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.websocket.core.listener.WebSocketMessageListener;
import cn.iocoder.yudao.framework.websocket.core.sender.WebSocketMessageSender;
import cn.iocoder.yudao.framework.websocket.core.util.WebSocketFrameworkUtils;
import cn.iocoder.yudao.module.infra.websocket.message.DemoReceiveMessage;
import cn.iocoder.yudao.module.infra.websocket.message.DemoSendMessage;
import cn.iocoder.yudao.module.infra.websocket.message.ImageReceiveMessage;
import cn.iocoder.yudao.module.infra.websocket.message.ImageSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocket 示例：单发消息
 *
 * @author 芋道源码
 */
@Component
public class ImageWebSocketMessageListener implements WebSocketMessageListener<ImageSendMessage> {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired(required = false) // 由于 yudao.websocket.enable 配置项，可以关闭 WebSocket 的功能，所以这里只能不强制注入
    private WebSocketMessageSender webSocketMessageSender;

    @Override
    public void onMessage(WebSocketSession session, ImageSendMessage message) {
        Long fromUserId = WebSocketFrameworkUtils.getLoginUserId(session);
        // 情况一：单发
        if (message.getToUserId() != null) {
            DemoReceiveMessage toMessage = new DemoReceiveMessage().setFromUserId(fromUserId)
                    .setText(message.getImgUrl()).setSingle(true);
            webSocketMessageSender.sendObject(UserTypeEnum.ADMIN.getValue(), message.getToUserId(), // 给指定用户
                    "demo-message-receive", toMessage);
            return;
        }
        // 情况二：群发
        ImageReceiveMessage toMessage = new ImageReceiveMessage().setFromUserId(fromUserId)
                .setImgUrl(message.getImgUrl()).setSingle(false);
        webSocketMessageSender.sendObject(UserTypeEnum.ADMIN.getValue(), // 给所有用户
                "demo-message-receive", toMessage);
    }

    @Override
    public String getType() {
        return "image";
    }

}
