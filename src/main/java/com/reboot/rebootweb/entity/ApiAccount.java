package com.reboot.rebootweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @TableName api_account
 */
@Data
@Setter
@Getter
@TableName(value = "api_account")
public class ApiAccount implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     *
     */
    private String apiKey;

    /**
     *
     */
    private String secretKey;

    /**
     * bitget需要口令
     */
    private String passphrase;

    /**
     * 0->binance 1->bitget
     */
    private String terrace;

    /**
     * 跟随账号
     */
    private Long follow;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 0->跟随账号 1->主账户
     */
    private Integer main;

    /**
     * 0->不在线  1->在线
     */
    private Integer isOnline;

    /**
     * 在线状态注释
     */
    private String onlineStatus;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiAccount that = (ApiAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(apiKey, that.apiKey) && Objects.equals(secretKey, that.secretKey) && Objects.equals(passphrase, that.passphrase) && Objects.equals(terrace, that.terrace) && Objects.equals(follow, that.follow) && Objects.equals(remarks, that.remarks) && Objects.equals(main, that.main) && Objects.equals(isOnline, that.isOnline) && Objects.equals(onlineStatus, that.onlineStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apiKey, secretKey, passphrase, terrace, follow, remarks, main, isOnline, onlineStatus);
    }


}