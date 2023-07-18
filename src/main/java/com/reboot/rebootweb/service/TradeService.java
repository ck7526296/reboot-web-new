package com.reboot.rebootweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reboot.rebootweb.entity.Trade;

/**
 * @author king
 * @description 针对表【trade】的数据库操作Service
 * @createDate 2023-05-14 20:04:58
 */
public interface TradeService extends IService<Trade> {
    void trade(Trade trade);

}
