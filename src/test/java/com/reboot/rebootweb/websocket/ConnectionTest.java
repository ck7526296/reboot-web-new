package com.reboot.rebootweb.websocket;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.reboot.rebootweb.bitget.openapi.dto.request.ws.SubscribeReq;
import com.reboot.rebootweb.bitget.openapi.ws.BitgetWsClient;
import com.reboot.rebootweb.bitget.openapi.ws.BitgetWsHandle;
import com.reboot.rebootweb.common.WebSocketRunClientCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.enums.ReadyState;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;

@Slf4j
public class ConnectionTest {

    public static final String PUSH_URL = "wss://ws.bitget.com/mix/v1/stream";

    public static final String API_KEY = "bg_2c12ba6bd624f7ac37f0c71be6cdc589";
    public static final String SECRET_KEY = "c3a21b25e9c79156337d88106934b36704efaf48855c43d6746adae2de72654d";
    public static final String PASS_PHRASE = "123qweQWE";

    @Test
    void connect() throws URISyntaxException, InterruptedException, JsonProcessingException {
        final WebSocketRunClientCreateUtil.WebSocket ws = WebSocketRunClientCreateUtil.webSocketClientCreate("", "wfw");
        ws.connect();
        log.info(String.valueOf(ws.getReadyState()));
        Thread.sleep(3000);
        ws.sendRequest("LGatvoVBJ5slODCGNO3DzBDmKR434z5nUh6NtPluwo2Wp8SyWejMXpQMxK5173Mb");
        log.info(String.valueOf(ws.getReadyState().equals(ReadyState.OPEN)));
        Thread.sleep(10000);
        ws.close();

    }

    public static void main(String[] args) {
        final BitgetWsClient client = BitgetWsHandle.builder()
                .pushUrl(PUSH_URL)
                .apiKey(API_KEY)
                .secretKey(SECRET_KEY)
                .passPhrase(PASS_PHRASE)
                .isLogin(true)
                .listener(response -> {
                    JSONObject json = JSONObject.parseObject(response);
                    System.out.println("def:" + json);
                    //失败消息的逻辑处理,如:订阅失败
                }).errorListener(response -> {
                    JSONObject json = JSONObject.parseObject(response);
                    System.out.println("error:" + json);
                })
                .build();

        final ArrayList<SubscribeReq> subscribeReqs = new ArrayList<>() {
            {
                add(SubscribeReq.builder().instType("UMCBL").channel("positions").instId("default").build());
            }
        };
        client.subscribe(subscribeReqs, response -> {
            JSONObject json = JSONObject.parseObject(response);
            System.out.println("appoint:" + json);
        });
    }
}
