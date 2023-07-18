package com.reboot.rebootweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reboot.rebootweb.common.CommonResult;
import com.reboot.rebootweb.entity.ApiAccount;
import com.reboot.rebootweb.service.ApiAccountService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class ApiAccountController {

    @Resource
    private ApiAccountService apiAccountService;

    @PostMapping
    private CommonResult<String> add(@RequestBody ApiAccount apiAccount) {
        final LambdaQueryWrapper<ApiAccount> apiAccountLambdaQueryWrapper = new LambdaQueryWrapper<>();
        apiAccountLambdaQueryWrapper.eq(StringUtils.isNotEmpty(apiAccount.getApiKey()), ApiAccount::getApiKey, apiAccount.getApiKey());
        final ApiAccount one = apiAccountService.getOne(apiAccountLambdaQueryWrapper);
        if (one != null) return CommonResult.failed("存在重复APiKey");
        final boolean save = apiAccountService.save(apiAccount);
        if (save) return CommonResult.success("","保存成功");
        return CommonResult.failed("未知错误");
    }

    @GetMapping
    private CommonResult<List<ApiAccount>> list(){
        final List<ApiAccount> list = apiAccountService.list(); ;
        return CommonResult.success(list);
    }

    @DeleteMapping
    private CommonResult<String> delete(@RequestBody ApiAccount apiAccount){
        final boolean b = apiAccountService.removeById(apiAccount);
        if (b) return CommonResult.success("","删除成功");
        return CommonResult.failed("删除失败");
    }
    @PutMapping
    private CommonResult<String> change(@RequestBody ApiAccount apiAccount){
        final boolean b = apiAccountService.updateById(apiAccount);
        if (b) return CommonResult.success("","更新成功");
        return CommonResult.failed("删除失败");
    }
}
