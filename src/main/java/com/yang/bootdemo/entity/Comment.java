package com.yang.bootdemo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author yang
 */
//@CompoundIndex(def = "{'userid:1','nickname:-1'}") 复合索引，索引可以添加，也可以直接在mongodb库中添加好
@Data
@Document(collection = "comment")//可以省略，若省略则使用类名小写
public class Comment {

    @Id
    private String id;

    @Field("content")
    private String content;

    private Date publishDate;

    @Indexed
    private String userid;

    private String nickname;

    private Date createdatetime;

    private Integer likenum;

    private Integer replynum;

    private String state;

    private String parentid;

    private String articleid;

}
