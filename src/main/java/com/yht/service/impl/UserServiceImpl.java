package com.yht.service.impl;

import com.yht.dao.IUserDao;
import com.yht.entity.User;
import com.yht.service.IUserService;
import com.yht.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserSerivceImpl
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 15:22
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public void insertUser(User user){
        user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
        userDao.insertUser(user);
    }

    @Override
    public User selectByNameAndPwd(User user) {
        user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
        return userDao.selectByNameAndPwd(user);
    }

    @Override
    public User selectByName(String name) {
        return userDao.selectByName(name);
    }
}