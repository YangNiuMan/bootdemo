package com.yang.bootdemo.controller;


import com.yang.bootdemo.entity.Person;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Import(value = {Person.class})
@RequestMapping
public abstract class BaseController {

    @PostMapping("/hello123")
    public String sayHello123(){
        return "hello123";
    }
}
