package com.reboot.rebootweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reboot.rebootweb.common.enums.ApiAccountEnums;
import com.reboot.rebootweb.entity.ApiAccount;
import com.reboot.rebootweb.service.ApiAccountService;
import com.reboot.rebootweb.mapper.ApiAccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author king
 * @description 针对表【api_account】的数据库操作Service实现
 * @createDate 2023-05-14 20:27:13
 */
@Service
public class ApiAccountServiceImpl extends ServiceImpl<ApiAccountMapper, ApiAccount>
        implements ApiAccountService {

    @Override
    public List<ApiAccount> getAllMainAccount() {
        final LambdaQueryWrapper<ApiAccount> apiAccountLambdaQueryWrapper = new LambdaQueryWrapper<>();
        apiAccountLambdaQueryWrapper.eq(ApiAccount::getMain, ApiAccountEnums.MAIN.getType());

        return this.list(apiAccountLambdaQueryWrapper);
    }

    @Override
    public void changeOnlineStatus(Long id, Integer isOnline, String onlineStatus) {
        getBaseMapper().updateOnlineStatusById(id, isOnline, onlineStatus);
    }
}




