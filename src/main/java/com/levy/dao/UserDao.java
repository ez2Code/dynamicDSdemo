package com.levy.dao;

import com.levy.dao.mapper.UserMapper;
import com.levy.entity.User;
import com.levy.mysql.DBSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by li_weia on 2017/7/7.
 */
@Repository
public class UserDao {
    private final UserMapper userMapper;

    @Autowired(required = false)
    public UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @DBSource("db-read")
    public List<User> getAllSite(){
        return userMapper.getAllSite();
    }

    @DBSource("db-master")
    public User getUserById(int id){
        return userMapper.getUserById(id);
    }
}
