package com.yang.bootdemo.controller;


import com.yang.bootdemo.entity.User;
import com.yang.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yang
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("insert")
    public int insert(@RequestBody User user){
        return userService.insert(user);
    }

    @GetMapping("find")
    public User find(@RequestParam("id") Integer id){
        return userService.find(id);
    }


}
