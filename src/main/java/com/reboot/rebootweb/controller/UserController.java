package com.reboot.rebootweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reboot.rebootweb.common.CommonResult;
import com.reboot.rebootweb.entity.User;
import com.reboot.rebootweb.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public CommonResult<User> login(HttpSession httpSession, @RequestBody User user){
        final LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(StringUtils.isNotEmpty(user.getName()),User::getName,user.getName());
        final User one = userService.getOne(userLambdaQueryWrapper);
        if (one==null||!one.getPassword().equals(user.getPassword())) return CommonResult.validateFailed("账号或者密码错误");
        httpSession.setAttribute("login",one);
        one.setId(0L);
        one.setPassword("");
        return CommonResult.success(one,"登陆成功");
    }
}
