package com.reboot.rebootweb.mapper;

import com.reboot.rebootweb.entity.ApiAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author king
* @description 针对表【api_account】的数据库操作Mapper
* @createDate 2023-05-14 20:27:13
* @Entity com.reboot.rebootweb.entity.ApiAccount
*/
public interface ApiAccountMapper extends BaseMapper<ApiAccount> {
    void updateOnlineStatusById(@Param("id") Long id,@Param("online") Integer online,@Param("onlineStatus") String onlineStatus);
}




