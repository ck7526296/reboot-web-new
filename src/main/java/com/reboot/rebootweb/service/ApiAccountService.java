package com.reboot.rebootweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reboot.rebootweb.entity.ApiAccount;

import java.util.List;

/**
* @author king
* @description 针对表【api_account】的数据库操作Service
* @createDate 2023-05-14 20:27:13
*/
public interface ApiAccountService extends IService<ApiAccount> {

     List<ApiAccount> getAllMainAccount();
     void changeOnlineStatus(Long id,Integer isOnline,String onlineStatus);
}
