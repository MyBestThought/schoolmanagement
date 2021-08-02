package com.yht.dao;

import com.yht.entity.Student;
import com.yht.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 16:19
 */
@Repository
public interface IStudentDao {
    void insertStudent(Student student);
    List<Student> selectAll();
    Student selectById(int id);
    void updateStudent(Student student);
    void deleteById(int id);
    List<Student> selectByCondition(Map<String, Object> map);
    List<String> selectClassCount(String stuNo);
}

