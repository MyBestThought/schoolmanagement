package com.yht.service.impl;

import com.yht.dao.IClassDao;
import com.yht.entity.ClassEntity;
import com.yht.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClassServiceImpl
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/29 8:39
 */
@Service
public class ClassServiceImpl implements IClassService {
    @Autowired
    private IClassDao classDao;
    @Override
    public void insertClass(ClassEntity classEntity) {
        classDao.insertClass(classEntity);
    }

    @Override
    public List<ClassEntity> selectAll() {
        return classDao.selectAll();
    }

    @Override
    public void deleteById(Integer id) {
        classDao.deleteById(id);
    }

    @Override
    public void updateClass(ClassEntity classEntity) {
        classDao.updateClass(classEntity);
    }

    @Override
    public ClassEntity selectById(Integer id) {
        return classDao.selectById(id);
    }

    @Override
    public List<ClassEntity> selectByCondition(String searchMajor, String searchClassNo) {
        Map<String, Object> map = new HashMap<>();
        if(searchMajor != ""){
            searchMajor = "%" + searchMajor + "%";
        }
        map.put("major", searchMajor);
        map.put("classNo", searchClassNo);
        return classDao.selectByCondition(map);
    }
}