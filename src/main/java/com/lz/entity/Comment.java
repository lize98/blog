package com.lz.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lize
 */

@Data
public class Comment {

    /**编号*/
    private Long id;

    /**昵称*/
    private String nickname;

    /**邮箱*/
    private String email;

    /**内容*/
    private String content;

    /**头像地址*/
    private String avatar;

    /**评论时间*/
    private Date createTime;

    /**是否是管理员评论*/
    private boolean adminComment;


    /**用于关联博客*/
    private Long blogId;
    /**父评论编号*/
    private Long parentCommentId;

    /**被回复人昵称*/
    private String replyName;

    /**记录回复的编号，例如5回复1，记录1*/
    private Long topCommentId;

    /**级联关系*/
    private Blog blog;

    private List<Comment> childComments = new ArrayList<>();

}
