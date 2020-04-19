package com.lz.service;

import com.lz.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    public List<Blog> getAllBlog();
    public void saveBlog(Blog blog);
    public Blog getBlogById(Long id);
    public void deleteBlog(Long id);
    public void updateBlog(Blog blog);
    public List<Blog> searchBlog(Blog blog);


    public Integer countBlog();
    public Map<String,List<Blog>> archiveBlog();


    public List<Blog> getBlogByTypeId(Long typeId);
    public List<Blog> getBlogByTagId(Long tagId);


    public List<Blog> getIndexBlog();
    public List<Blog> getRecommendBlog();
    public List<Blog> searchIndexBlog(String query);
    public Blog getDetailedBlog(Long id);


}
