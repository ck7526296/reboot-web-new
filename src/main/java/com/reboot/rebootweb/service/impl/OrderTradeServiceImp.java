package com.reboot.rebootweb.service.impl;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.reboot.rebootweb.entity.response.BinancePositionChange;
import com.reboot.rebootweb.service.OrderTradeService;

public class OrderTradeServiceImp implements OrderTradeService {
    private UMFuturesClientImpl umFuturesClient = new UMFuturesClientImpl();

    @Override
    public void Trade(BinancePositionChange binancePositionChange) {

    }
}
