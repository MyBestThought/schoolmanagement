package com.yht.service;

import com.yht.entity.ClassEntity;
import com.yht.entity.Curriculum;

import java.util.List;

/**
 * @ClassName IClassService
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/29 8:38
 */
public interface IClassService {
    void insertClass(ClassEntity classEntity);
    List<ClassEntity> selectAll();
    void deleteById(Integer id);
    void updateClass(ClassEntity classEntity);
    ClassEntity selectById(Integer id);
    List<ClassEntity> selectByCondition(String searchMajor,  String searchClassNo);
}
