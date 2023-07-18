package com.reboot.rebootweb.getreboot.binance;

import com.alibaba.fastjson2.JSONObject;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.binance.connector.futures.client.impl.UMWebsocketClientImpl;
import com.reboot.rebootweb.common.enums.ApiAccountEnums;
import com.reboot.rebootweb.common.enums.BinanceEnums;
import com.reboot.rebootweb.entity.ApiAccount;
import com.reboot.rebootweb.entity.Trade;
import com.reboot.rebootweb.service.ApiAccountService;
import com.reboot.rebootweb.service.TradeService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BinanceUserStream {
    private final ApiAccount apiAccount;

    public ApiAccount getApiAccount() {
        return apiAccount;
    }

    private int connectId;
    private int connectCount = 0;
    private long lastConnectTime = 0;
    private static final int MAX_RECONNECT_COUNT = 3;
    private final UMWebsocketClientImpl websocketClient;
    private final UMFuturesClientImpl umFuturesClient;


    private final TradeService tradeService;
    private final ApiAccountService apiAccountService;

    public BinanceUserStream(ApiAccount apiAccount, TradeService tradeService, ApiAccountService apiAccountService) {
        this.tradeService = tradeService;
        this.apiAccountService = apiAccountService;
        this.apiAccount = apiAccount;
        umFuturesClient = new UMFuturesClientImpl(apiAccount.getApiKey(), apiAccount.getSecretKey());
        websocketClient = new UMWebsocketClientImpl();
        this.connectId = listenUserStream();
    }


    public void extendListenKey() {
        umFuturesClient.userData().extendListenKey();
    }

    public void reconnect() {
        websocketClient.closeConnection(connectId);
        if (this.connectCount < MAX_RECONNECT_COUNT) {
            log.info("{}第{}次重连", apiAccount.getRemarks(), connectCount + 1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            connectId = listenUserStream();
            if (System.currentTimeMillis() - 60 * 5 < this.lastConnectTime) {
              
                log.info("{}重连过快", apiAccount.getRemarks());
                this.connectCount++;
            } else {
                this.connectCount = 0;
            }
        } else {
            log.info("{}失去重连资格", apiAccount.getRemarks());
        }

    }

    public int listenUserStream() {
        return websocketClient.listenUserStream(
                getListenKey(),
                open -> {
                    log.info("{}用户开始监听", apiAccount.getRemarks());
                    apiAccountService.changeOnlineStatus(apiAccount.getId(), ApiAccountEnums.ONLINE.getType(), "监听中");
                    this.lastConnectTime = System.currentTimeMillis();
                },
                onMessage -> {
                    System.out.println(onMessage);
                    JSONObject message = JSONObject.parseObject(onMessage);
                    JSONObject order = message.getJSONObject("o");

                    if (
                            BinanceEnums.ORDER_TRADE_UPDATE.getName().equals(message.getString("e")) &&
                                    BinanceEnums.TRADE.getName().equals(order.getString("x"))
                    ) {
                        tradeService.trade(new Trade(
                                order.getString("S"),
                                order.getString("ot"),
                                order.getString("s"),
                                order.getBigDecimal("q"),
                                order.getLong("E"),
                                this.apiAccount.getId()
                        ));
                    }
                },
                close -> {
                    log.info("{}用户停止连接", apiAccount.getRemarks());
                    apiAccountService.changeOnlineStatus(apiAccount.getId(), ApiAccountEnums.ONLINE.getType(), "停止连接");
                },
                failure -> {
                    log.info("{}用户连接失败", apiAccount.getRemarks());
                    apiAccountService.changeOnlineStatus(apiAccount.getId(), ApiAccountEnums.ONLINE.getType(), "连接失败");
                    this.reconnect();
                }
        );
    }

    private String getListenKey() {
        return JSONObject.parseObject(umFuturesClient.userData().createListenKey()).getString("listenKey");
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    private void trade(Trade trade) {
//        umFuturesClient.account().newOrder(parameters);
    }

    public void close() {
        websocketClient.closeConnection(connectId);
    }
}
