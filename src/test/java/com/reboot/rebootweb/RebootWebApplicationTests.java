package com.reboot.rebootweb;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.reboot.rebootweb.service.ApiAccountService;
import com.reboot.rebootweb.service.TradeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;

@SpringBootTest
class RebootWebApplicationTests {
    String API = "qD6h9P2jAl3JFYV55AylQFZgZ73Sc6RgJG8lQJ4jS7imlZxJI4oZpSekjpStLmAt";
    String Secret = "skXYdAIuxXS2zx5zNbpxohqpPbacCaS1dhxj0MB42El1nIZVL3fn87MLTAJaKQuu";
    @Resource
    private ApiAccountService apiAccountService;
    @Resource
    private TradeService tradeService;

    @Test
    void s(){
        final UMFuturesClientImpl umFuturesClient = new UMFuturesClientImpl(API, Secret);
        final LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
//        parameters.put("side", "BUY");
//        parameters.put("quantity", 0.010);
//        parameters.put("type", "MARKET");
        System.out.println(umFuturesClient.account().allOrders(parameters));
    }

    @Test
    void contextLoads() throws InterruptedException {
        Thread.sleep(1000*10000);
    }

    public static void main(String[] args) throws InterruptedException {
//        String API = "qD6h9P2jAl3JFYV55AylQFZgZ73Sc6RgJG8lQJ4jS7imlZxJI4oZpSekjpStLmAt";
//        String Secret = "skXYdAIuxXS2zx5zNbpxohqpPbacCaS1dhxj0MB42El1nIZVL3fn87MLTAJaKQuu";
//        final UMFuturesClientImpl umFuturesClient = new UMFuturesClientImpl(API, Secret);
//        final LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//        parameters.put("symbol", "BTCUSDT");
//        parameters.put("side", "BUY");
//        parameters.put("quantity", 0.010);
//        parameters.put("type", "MARKET");
//        System.out.println(umFuturesClient.account().newOrder(parameters));
//        while (true) {
//            Thread.sleep(10000);
//        }
    }
}
