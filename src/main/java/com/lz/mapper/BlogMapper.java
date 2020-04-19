package com.lz.mapper;

import com.lz.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogMapper {
    public List<Blog> getAllBlog();
    public void saveBlog(Blog blog);
    public Blog getBlogById(@Param("id") Long id);
    public void deleteBlog(Long id);
    public void updateBlog(Blog blog);
    public List<Blog> searchBlog(Blog blog);


    public Integer countBlog();
    public List<String> findGroupYear();
    public List<Blog> findByYear(@Param("year")String year);


    public List<Blog> getBlogByTypeId(@Param("typeId")Long typeId);
    public List<Blog> getBlogByTagId(@Param("tagId")Long tagId);

    public List<Blog> getIndexBlog();
    public List<Blog> getRecommendBlog();
    public List<Blog> searchIndexBlog(@Param("query")String query);
    public Blog getDetailedBlog(@Param("id")Long id);
    public void updateViews(@Param("id")Long id);
}
