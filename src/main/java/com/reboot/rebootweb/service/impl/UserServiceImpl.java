package com.reboot.rebootweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reboot.rebootweb.entity.User;
import com.reboot.rebootweb.service.UserService;
import com.reboot.rebootweb.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author king
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-15 15:06:30
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




