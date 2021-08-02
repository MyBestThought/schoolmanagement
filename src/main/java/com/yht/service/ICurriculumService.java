package com.yht.service;

import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ICurriculumService
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 14:58
 */
public interface ICurriculumService {
    void insertCurriculum(Curriculum curriculum);
    List<Curriculum> selectAll();
    void deleteById(Integer id);
    void updateCurriculum(Curriculum curriculum);
    Curriculum selectById(Integer id);
    List<Curriculum> selectByCondition(String searchClassName,  String searchClass);
    Curriculum selectByCurriculumNo(String classNo);
    List<Curriculum> selectByStuNo(String stuNo);
    List<Curriculum> selectByTeacherNo(String teacherNo);


}
