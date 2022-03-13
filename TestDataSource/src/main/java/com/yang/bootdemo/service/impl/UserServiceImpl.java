package com.yang.bootdemo.service.impl;

import com.yang.bootdemo.entity.User;
import com.yang.bootdemo.mapper.db1.UserMapper1;
import com.yang.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper1 userMapper1;


    @Override
    public int insert(User user) {
        return userMapper1.insert(user);
    }

    @Override
    public User find(Integer id) {
        return userMapper1.find(id);
    }

}
