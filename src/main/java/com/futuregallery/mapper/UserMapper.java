package com.futuregallery.mapper;

import com.futuregallery.model.Student;
import com.futuregallery.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User login(Map<String, String> map);

    List<User> getAllUser();

    User selectByUser(User user);

    int updatePwd(User user);
}
