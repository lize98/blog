package com.lz.mapper;

import com.lz.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentMapper {
    public void saveComment(Comment comment);
    public Comment getCommentByIdAndBlogId(@Param("id")Long id,@Param("blogId") Long blogId);

    public List<Comment> getCommentByParentCommentIdAndBlogId(@Param("parentCommentId")Long parentCommentId,@Param("blogId")Long blogId);
    public List<Comment> getCommentByTopCommentAndBlogId(@Param("topCommentId")Long topCommentId,@Param("blogId")Long blogId);
}
