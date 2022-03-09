package com.yang.bootdemo.mapper.db1;

import com.yang.bootdemo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author yang
 */
@Repository
public interface UserMapper1 {

    int insert(User user);
}
