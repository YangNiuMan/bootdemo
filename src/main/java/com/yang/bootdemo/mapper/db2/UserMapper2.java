package com.yang.bootdemo.mapper.db2;

import com.yang.bootdemo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author yang
 */
@Repository
public interface UserMapper2 {

    User find(Integer id);
}
