package com.yht.dao;

import com.yht.entity.ClassEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IClassDao
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/29 8:38
 */
@Repository
public interface IClassDao {
    void insertClass(ClassEntity classEntity);
    List<ClassEntity> selectAll();
    void deleteById(Integer id);
    void updateClass(ClassEntity classEntity);
    ClassEntity selectById(Integer id);
    List<ClassEntity> selectByCondition(Map<String, Object> map);
}