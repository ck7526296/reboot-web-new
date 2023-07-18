package com.reboot.rebootweb.common.enums;

public enum ApiAccountEnums {
    MAIN(1),
    FLOW(0),
    ERROR(2),
    ONLINE(1),
    OFFLINE(0),
    BITGET(1),
    Binance(0);
    private final Integer type;

    private ApiAccountEnums(int type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
