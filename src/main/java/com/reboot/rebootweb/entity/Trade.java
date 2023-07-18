package com.reboot.rebootweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName trade
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName(value = "trade")
public class Trade implements Serializable {
    public Trade(String side, String type, String symbol, BigDecimal quantity, Long createTime, Long accountId) {
        this.side = side;
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.createTime = new Date(createTime);
        this.accountId = accountId;
    }

    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 订单方向
     */
    private String side;

    /**
     * 订单类型
     */
    private String type;

    /**
     * 交易对
     */
    private String symbol;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 创建订单账号
     */
    private Long accountId;

    /**
     * 跟随主账号id，id为0代表主用户创建
     */
    private Long flowAccountId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}