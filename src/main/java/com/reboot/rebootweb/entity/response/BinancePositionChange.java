package com.reboot.rebootweb.entity.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BinancePositionChange {
    private String symbol;
    private String side;
    private BigDecimal quantity;
    private String type;
    private LocalDateTime localDateTime;

    public static void main(String[] args) {
        BinancePositionChange binancePositionChange = new BinancePositionChange();

    }
}
