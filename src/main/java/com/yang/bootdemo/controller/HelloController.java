package com.yang.bootdemo.controller;


import com.yang.bootdemo.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 */
@Log
@RestController
public class HelloController  extends BaseController {

    @RequestMapping("hello")
    public String hello(String message){
        return message;
    }

}
