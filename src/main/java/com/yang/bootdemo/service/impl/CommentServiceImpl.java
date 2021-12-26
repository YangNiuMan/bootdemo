package com.yang.bootdemo.service.impl;

import com.yang.bootdemo.dao.CommentDao;
import com.yang.bootdemo.entity.Comment;
import com.yang.bootdemo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author yang
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    public Iterable<Comment> findAll(Sort sort) {
        return commentDao.findAll(sort);
    }

    public Page<Comment> findAll(Pageable pageable) {
        return commentDao.findAll(pageable);
    }

    public <S extends Comment> S save(S entity) {
        return commentDao.save(entity);
    }

    public Optional<Comment> findById(String s) {
        return commentDao.findById(s);
    }

    public boolean existsById(String s) {
        return commentDao.existsById(s);
    }

    public Iterable<Comment> findAll() {
        return commentDao.findAll();
    }

    public Iterable<Comment> findAllById(Iterable<String> strings) {
        return commentDao.findAllById(strings);
    }

    public long count() {
        return commentDao.count();
    }

    public void deleteById(String s) {
        commentDao.deleteById(s);
    }

    public void delete(Comment entity) {
        commentDao.delete(entity);
    }

    public void deleteAllById(Iterable<? extends String> strings) {
        commentDao.deleteAllById(strings);
    }

    public void deleteAll(Iterable<? extends Comment> entities) {
        commentDao.deleteAll(entities);
    }

    public void deleteAll() {
        commentDao.deleteAll();
    }


}
