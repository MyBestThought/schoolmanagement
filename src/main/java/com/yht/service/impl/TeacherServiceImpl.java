package com.yht.service.impl;

import com.yht.dao.ITeacherDao;
import com.yht.entity.Teacher;
import com.yht.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherServiceImpl
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 19:22
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private ITeacherDao teacherDao;

    @Override
    public List<Teacher> selectAll() {
        return teacherDao.selectAll();
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teacherDao.insertTeacher(teacher);
    }

    @Override
    public void deleteById(Integer id) {
        teacherDao.deleteById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDao.updateTeacher(teacher);
    }

    @Override
    public Teacher selectById(Integer id) {
        return teacherDao.selectById(id);
    }

    @Override
    public List<Teacher> selectByCondition(String searchName, String searchTeacherNo, String searchProfessor) {
        Map<String, Object> map = new HashMap<>();
        if(searchName != "" && !StringUtils.isEmpty(searchName)){
            searchName = "%" + searchName + "%";
        }
        map.put("name", searchName);
        map.put("teacherNo", searchTeacherNo);
        map.put("professor", searchProfessor);
        return teacherDao.selectByCondition(map);
    }
}