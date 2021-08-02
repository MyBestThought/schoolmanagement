package com.yht.service;

import com.yht.entity.Teacher;

import java.util.List;

/**
 * @ClassName ITeacherService
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 19:21
 */
public interface ITeacherService {
    List<Teacher> selectAll();
    void insertTeacher(Teacher teacher);
    void deleteById(Integer id);
    void updateTeacher(Teacher teacher);
    Teacher selectById(Integer id);
    List<Teacher> selectByCondition(String searchName, String searchTeacherNo, String searchProfessor);
}