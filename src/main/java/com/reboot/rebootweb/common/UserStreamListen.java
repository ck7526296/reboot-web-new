package com.reboot.rebootweb.common;

import com.reboot.rebootweb.entity.ApiAccount;
import com.reboot.rebootweb.getreboot.binance.BinanceUserStream;
import com.reboot.rebootweb.service.ApiAccountService;
import com.reboot.rebootweb.service.TradeService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserStreamListen {
    @Resource
    private ApiAccountService apiAccountService;
    @Resource
    private TradeService tradeService;
    private static final Map<Long, BinanceUserStream> accountMap = new HashMap<>();

    @Scheduled(fixedDelay = 1000 * 2)
    public void updateListenAccountList() {
        //  获得所有主账户
        final List<ApiAccount> allMainAccount = apiAccountService.getAllMainAccount();
        final Set<Long> longs = new HashSet<>();
        // 开始监听主账户
        for (ApiAccount apiAccount : allMainAccount) {
            if (!accountMap.containsKey(apiAccount.getId())) {
                accountMap.put(apiAccount.getId(), new BinanceUserStream(apiAccount, tradeService,apiAccountService));
            } else if (!apiAccount.equals(accountMap.get(apiAccount.getId()).getApiAccount())) {
                accountMap.get(apiAccount.getId()).close();
                accountMap.put(apiAccount.getId(), new BinanceUserStream(apiAccount, tradeService,apiAccountService));
            }
            longs.add(apiAccount.getId());
        }
        final Set<Long> listenAccount = new HashSet<>(accountMap.keySet());
        // 删除无需监听专户
        listenAccount.removeAll(longs);
        if (listenAccount.size() != 0) {
            for (Long aLong : listenAccount) {
                accountMap.get(aLong).close();
            }
        }

    }

    @Scheduled(fixedDelay = 1000*60*45)
    public void extendListenKey(){
        accountMap.forEach((aLong, binanceUserStream) -> binanceUserStream.extendListenKey());
    }
}
