package com.reboot.rebootweb.service;

import com.reboot.rebootweb.entity.response.BinancePositionChange;

public interface OrderTradeService {
    void Trade(BinancePositionChange binancePositionChange);
}
