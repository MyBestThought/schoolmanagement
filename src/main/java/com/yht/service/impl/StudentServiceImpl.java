package com.yht.service.impl;

import com.yht.dao.IStudentDao;
import com.yht.dao.IUserDao;
import com.yht.entity.Student;
import com.yht.entity.User;
import com.yht.service.IStudentService;
import com.yht.service.IUserService;
import com.yht.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserSerivceImpl
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 15:22
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public Student selectById(int id) {
        return studentDao.selectById(id);
    }

    @Override
    public List<Student> selectAll() {
        return studentDao.selectAll();
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<Student> selectByCondition(String searchName, String searchStuNo, String searchClass) {
        Map<String, Object> map = new HashMap<>();
        if(searchName != "" && !StringUtils.isEmpty(searchName)){
            searchName = "%" + searchName + "%";
        }
        map.put("name", searchName);
        map.put("stuNo", searchStuNo);
        map.put("classNo", searchClass);
        return studentDao.selectByCondition(map);
    }

    @Override
    public List<String>  selectClassCount(String stuNo) {
        return studentDao.selectClassCount(stuNo);
    }
}