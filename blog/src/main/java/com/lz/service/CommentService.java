package com.lz.service;

import com.lz.entity.Comment;

import java.util.List;

public interface CommentService {
    public void saveComment(Comment comment);
    public List<Comment> getCommentByBlogId(Long blogId);
}
