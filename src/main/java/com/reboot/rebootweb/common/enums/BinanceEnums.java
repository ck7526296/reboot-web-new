package com.reboot.rebootweb.common.enums;

public enum BinanceEnums {
    BUY("BUY"),
    SELL("SELL"),
    MARKET("MARKET"),
    TRADE("TRADE"),
    ORDER_TRADE_UPDATE("ORDER_TRADE_UPDATE"),
    quantity("quantity"),
    type("type"),
    symbol("symbol"),
    side("side");

    private final String name;

    private BinanceEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
