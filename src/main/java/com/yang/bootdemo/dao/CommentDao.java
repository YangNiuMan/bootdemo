package com.yang.bootdemo.dao;

import com.yang.bootdemo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yang
 */
public interface CommentDao extends PagingAndSortingRepository<Comment,String> {

    Page<Comment> findByParentid(String parentid, Pageable pageable);
}
