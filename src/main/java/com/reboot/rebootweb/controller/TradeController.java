package com.reboot.rebootweb.controller;

import com.reboot.rebootweb.common.CommonResult;
import com.reboot.rebootweb.entity.Trade;
import com.reboot.rebootweb.service.TradeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Resource
    private TradeService tradeService;

    @GetMapping
    private CommonResult<List<Trade>> get() {
        return CommonResult.success(tradeService.list());
    }

    @DeleteMapping
    private CommonResult<String> delete(@RequestBody Trade trade) {
        final boolean b = tradeService.removeById(trade);
        if (b) return CommonResult.success("", "删除成功");
        return CommonResult.failed("删除失败");
    }
}
