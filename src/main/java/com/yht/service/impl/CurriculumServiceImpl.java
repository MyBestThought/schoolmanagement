package com.yht.service.impl;

import com.yht.dao.ICurriculumDao;
import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import com.yht.service.ICurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CurriculumServiceImpl
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 14:58
 */
@Service
public class CurriculumServiceImpl implements ICurriculumService {
    @Autowired
    private ICurriculumDao curriculumDao;
    @Override
    public void insertCurriculum(Curriculum curriculum) {
        curriculumDao.insertCurriculum(curriculum);
    }

    @Override
    public List<Curriculum> selectAll() {
        return curriculumDao.selectAll();
    }

    @Override
    public void deleteById(Integer id) {
        curriculumDao.deleteById(id);
    }

    @Override
    public void updateCurriculum(Curriculum curriculum) {
        curriculumDao.updateCurriculum(curriculum);
    }

    @Override
    public Curriculum selectById(Integer id) {
        return curriculumDao.selectById(id);
    }

    @Override
    public List<Curriculum> selectByCondition(String searchClassName, String searchClass) {
        Map<String, Object> map = new HashMap<>();
        if(searchClassName != "" && !StringUtils.isEmpty(searchClassName)){
            searchClassName = "%" + searchClassName + "%";
        }
        map.put("className", searchClassName);
        map.put("classNo", searchClass);
        System.out.println(map);
        return curriculumDao.selectByCondition(map);
    }

    @Override
    public Curriculum selectByCurriculumNo(String classNo) {
        return curriculumDao.selectByCurriculumNo(classNo);
    }

    @Override
    public List<Curriculum> selectByStuNo(String stuNo) {
        return curriculumDao.selectByStuNo(stuNo);
    }

    @Override
    public List<Curriculum> selectByTeacherNo(String teacherNo) {
        return curriculumDao.selectByTeacherNo(teacherNo);
    }
}