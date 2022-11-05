package com.yang.bootdemo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello(String message){
        System.out.println(message);
        System.out.println(message);
        System.out.println(message+"222");
        System.out.println(message+"333");
        System.out.println(message+"555");
        System.out.println(message+"999");
        System.out.println(message+"101010");
        return message;
    }


}
