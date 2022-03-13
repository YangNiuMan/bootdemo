package com.yang.bootdemo.controller;


import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 */
@EnableAsync
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello(String message){
        return message;
    }

}
