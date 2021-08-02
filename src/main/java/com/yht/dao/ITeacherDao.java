package com.yht.dao;

import com.yht.entity.Curriculum;
import com.yht.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ITeacherDao
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 19:21
 */
@Repository
public interface ITeacherDao {
    List<Teacher> selectAll();
    void insertTeacher(Teacher teacher);
    void deleteById(Integer id);
    void updateTeacher(Teacher teacher);
    List<Teacher> selectByCondition(Map<String, Object> map);
    Teacher selectById(Integer id);

}
