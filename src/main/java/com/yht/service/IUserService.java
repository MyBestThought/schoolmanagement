package com.yht.service;

import com.yht.entity.User;

/**
 * @ClassName IUserService
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 15:21
 */
public interface IUserService {
    void insertUser(User user);
    User selectByNameAndPwd(User user);
    User selectByName(String name);
}
