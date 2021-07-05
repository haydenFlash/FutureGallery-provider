package com.futuregallery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.futuregallery.exception.LoginException;
import com.futuregallery.mapper.UserMapper;
import com.futuregallery.model.User;
import com.futuregallery.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@Service(interfaceClass = UserService.class, version = "1.0.0", timeout = 15000)
public class UserServiceImpl implements UserService {
    @Resource
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

    @Override
    public Map<String, Object> checkPwd(User user) {
        boolean flag = true;
        String msg = null;

        User user1 = userMapper.selectByUser(user);
        if (user1 == null) {
            flag = false;
            msg = "原密码错误！";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("msg", msg);

        return map;
    }

    @Override
    public Boolean editPwd(User user) {
        boolean flag = true;
        int count = userMapper.updatePwd(user);
        if (count != 1) {
            flag = false;
        }

        return flag;
    }
}
