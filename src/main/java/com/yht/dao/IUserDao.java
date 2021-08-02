package com.yht.dao;

import com.yht.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 16:19
 */
@Repository
public interface IUserDao {
    void insertUser(User user);
    User selectByNameAndPwd(User user);
    User selectByName(String name);
}

