package com.futuregallery.mapper;

import com.futuregallery.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> queryAllStudent();

    int queryStudentCount();

    List<Student> selectSelective(Map<String, Object> map);

    int selectTotalSelective(Map<String, Object> map);

    int deleteByIds(String[] ids);
}