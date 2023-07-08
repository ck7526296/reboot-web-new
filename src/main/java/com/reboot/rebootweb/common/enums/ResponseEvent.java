package com.reboot.rebootweb.common.api;

public enum ResponseEvent {
    ListenKeyExpired("listenKeyExpired"),
    ACCOUNT_UPDATE("ACCOUNT_UPDATE"),
    ORDER_TRADE_UPDATE("ORDER_TRADE_UPDATE"),
    ACCOUNT_CONFIG_UPDATE("ACCOUNT_CONFIG_UPDATE");
    final String event;

    private ResponseEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return this.event;
    }
}
