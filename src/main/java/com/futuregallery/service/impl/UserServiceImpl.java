package com.futuregallery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.futuregallery.exception.LoginException;
import com.futuregallery.mapper.UserMapper;
import com.futuregallery.model.User;
import com.futuregallery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Service(interfaceClass = UserService.class, version = "1.0.0", timeout = 15000)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String loginName, String loginPwd) throws LoginException {
        Map<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("loginPwd", loginPwd);

        User user = userMapper.login(map);

        if (user == null) {
            throw new LoginException("账号或密码错误");
        } else {
            return user;
        }
    }
}
