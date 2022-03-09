package com.yang.bootdemo.service;

import com.yang.bootdemo.entity.User;

/**
 * @author yang
 */
public interface UserService {

    int insert(User user);

    User find(Integer id);
}
