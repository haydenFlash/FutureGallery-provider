package com.futuregallery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.futuregallery.mapper.StudentMapper;
import com.futuregallery.mapper.UserMapper;
import com.futuregallery.model.Student;
import com.futuregallery.model.User;
import com.futuregallery.service.StudentService;
import com.futuregallery.vo.PaginationVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service(interfaceClass = StudentService.class, version = "1.0.0", timeout = 15000)
public class StudentServiceImpl implements StudentService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<User> getAllUser() {

        return userMapper.getAllUser();
    }

    @Override
    public boolean addStudent(Student student) {

        boolean flag = true;

        int resCount = studentMapper.insert(student);

        if (resCount != 1) {
            flag = false;
        }

        return flag;
    }

    @Override
    public PaginationVO pageList(Map<String, Object> map) {
        //根据搜索栏提供的值来选择性的取出学员信息
        List<Student> dataList = studentMapper.selectSelective(map);

        //根据用户选择的页码来取出当页学员信息
        int total = studentMapper.selectTotalSelective(map);

        PaginationVO vo = new PaginationVO();
        vo.setDataList(dataList);
        vo.setTotal(total);

        return vo;
    }

    @Override
    public Boolean deleteStudent(String[] ids) {
        boolean flag = true;

        int count = studentMapper.deleteByIds(ids);
        if (count != ids.length) {
            flag = false;
        }

        return flag;
    }

    @Override
    public Map<String, Object> editStudent(String id) {
        List<User> userList = userMapper.getAllUser();
        Student student = studentMapper.selectByPrimaryKey(id);

        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("student", student);
        return map;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean flag = true;

        int count = studentMapper.updateByPrimaryKeySelective(student);
        if (count != 1) {
            flag = false;
        }

        return flag;
    }

    @Override
    public Student getStudent(String id) {
        return studentMapper.selectById(id);
    }
}
