package com.yang.bootdemo.controller;


import com.yang.bootdemo.entity.TbTest;
import com.yang.bootdemo.service.TbTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yang
 */
@RestController
public class HelloController {

    @Autowired
    private TbTestService tbTestService;

    @RequestMapping("hello")
    public String hello(String message){
        return message;
    }

    @GetMapping("select")
    public Collection<TbTest> select(@RequestBody List<String> tbList){
        return tbTestService.listByIds(tbList).stream().collect(Collectors.toList());
    }

    @PostMapping("insert")
    public void insert(@RequestBody TbTest tbTest){
        tbTestService.save(tbTest);
    }


    @DeleteMapping("delete")
    public void delete(String id){
        tbTestService.removeById(id);
    }


    @PostMapping("update")
    public void update(@RequestBody TbTest tbTest){
        tbTestService.updateById(tbTest);
    }

}
