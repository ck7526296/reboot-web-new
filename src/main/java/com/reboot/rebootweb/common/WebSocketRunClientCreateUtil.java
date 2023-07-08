package com.reboot.rebootweb.common;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;

import java.awt.image.Kernel;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class WebSocketRunClient {

    private final static String FSTREAM_API = "wss://fstream.binance.com/ws/";

    private WebSocketRunClient() {
    }

    public static WebSocketClient webSocketClientCreate(String key, String userName) throws URISyntaxException {
        return new WebSocket(new URI(FSTREAM_API + key), userName);
    }

    private static class WebSocket extends WebSocketClient {
        private final String userName;

        public WebSocket(URI serverUri, String userName) {
            super(serverUri);
            this.userName = userName;
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {
            log.info("{} 连接WebSocket成功", this.userName);
        }

        @Override
        public void onMessage(String s) {
            log.info("{}收到消息：{}", userName, s);
        }

        @Override
        public void onClose(int i, String s, boolean b) {
            log.info("{} WebSocket连接关闭", this.userName);
        }

        @Override
        public void onError(Exception e) {
            log.info("{} 发生错误", this.userName);
            e.printStackTrace();
        }
    }
}
