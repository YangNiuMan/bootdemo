package com.yang.bootdemo;

import com.yang.bootdemo.dao.CommentDao;
import com.yang.bootdemo.entity.Comment;
import com.yang.bootdemo.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BootdemoApplicationTests {

    @Autowired
    CommentDao commentDao;

    @Test
    void contextLoads() {
        Iterable<Comment> all = commentDao.findAll();
        for (Comment comment : all) {
            System.out.println(comment.getId());

        }
        System.out.println(">>>>>>>>>>>>>>>>>");
        commentDao.deleteById("4");
        all = commentDao.findAll();
        for (Comment comment : all) {
            System.out.println(comment.getId());
        }
        Page<Comment> byParentid = commentDao.findByParentid("1", PageRequest.of(1, 1));
        System.out.println(byParentid.getTotalElements());
        for (Comment comment : byParentid) {
            System.out.println(">>>>>>>>>>>"+comment);
        }
    }



}
