package com.reboot.rebootweb.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reboot.rebootweb.common.enums.BinanceEnums;
import com.reboot.rebootweb.entity.ApiAccount;
import com.reboot.rebootweb.entity.Trade;
import com.reboot.rebootweb.mapper.TradeMapper;
import com.reboot.rebootweb.service.ApiAccountService;
import com.reboot.rebootweb.service.TradeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author king
 * @description 针对表【trade】的数据库操作Service实现
 * @createDate 2023-05-14 20:04:58
 */
@Slf4j
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade>
        implements TradeService {

    @Resource
    private ApiAccountService apiAccountService;

    @Override
    public void trade(Trade trade) {


        final LambdaQueryWrapper<ApiAccount> getAllAccountByMainId = new LambdaQueryWrapper<>();
        getAllAccountByMainId.eq(ApiAccount::getMain, trade.getAccountId());
        final List<ApiAccount> allFlowAccount = apiAccountService.list(getAllAccountByMainId);


        final LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(BinanceEnums.symbol.getName(), trade.getSymbol());
        parameters.put(BinanceEnums.side.getName(), trade.getSide());
        parameters.put(BinanceEnums.quantity.getName(), trade.getQuantity());
        parameters.put(BinanceEnums.type.getName(), trade.getType());
        log.info("{}收到订单更新信息{}", trade.getAccountId(), JSONObject.toJSONString(parameters));

    }



}




