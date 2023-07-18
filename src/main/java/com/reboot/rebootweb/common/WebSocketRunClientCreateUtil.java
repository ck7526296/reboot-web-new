package com.reboot.rebootweb.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reboot.rebootweb.entity.request.UserData;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
public class WebSocketRunClientCreateUtil {

    private final static String FSTREAM_API = "wss://dstream.binance.com/ws/";

    private WebSocketRunClientCreateUtil() {
    }

    public static WebSocket webSocketClientCreate(String key, String userName) throws URISyntaxException {
        log.info(FSTREAM_API + key);
        return new WebSocket(new URI(FSTREAM_API ), userName);
    }

    public static class WebSocket extends WebSocketClient {
        private final String userName;
        private static final ObjectMapper objectMapper = new ObjectMapper();

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


        public void sendRequest(String s) {
            try {
                final List<String> params = List.of(s + "@position");
                final UserData userData = new UserData("REQUEST", params, 34L);
                String json =objectMapper.writeValueAsString(userData);
                log.info(json);
                this.send(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
