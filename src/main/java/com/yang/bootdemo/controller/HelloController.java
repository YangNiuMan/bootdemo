package com.yang.bootdemo.controller;


import com.yang.bootdemo.service.DiscountStrategy;
import com.yang.bootdemo.service.impl.DisCountStrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 */
@RestController
public class HelloController {

    @Autowired
    DisCountStrageService strageService;

    @RequestMapping("hello")
    public String hello(String message){
        return message;
    }

    @RequestMapping("discount")
    public double discount(String type, double fee){

        return strageService.discount(type, fee);
    }

}
