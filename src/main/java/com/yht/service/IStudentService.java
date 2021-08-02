package com.yht.service;

import com.yht.entity.Student;
import com.yht.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IUserService
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 15:21
 */
public interface IStudentService {
    void insertStudent(Student student);
    Student selectById(int id);
    List<Student> selectAll();
    void updateStudent(Student student);
    void deleteById(Integer id);
    List<Student> selectByCondition(String searchName, String searchStuNo, String searchClass);
    List<String>  selectClassCount(String stuNo);


}
