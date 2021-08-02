package com.yht.dao;

import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ICurriculumDao
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 14:57
 */
@Repository
public interface ICurriculumDao {
    void insertCurriculum(Curriculum curriculum);
    List<Curriculum> selectAll();
    void deleteById(Integer id);
    void updateCurriculum(Curriculum curriculum);
    Curriculum selectById(Integer id);
    List<Curriculum> selectByCondition(Map<String, Object> map);
    Curriculum selectByCurriculumNo(String classNo);
    List<Curriculum> selectByStuNo(String stuNo);
    List<Curriculum> selectByTeacherNo(String teacherNo);
}
