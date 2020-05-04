package com.lz.service.impl;

import com.lz.NotFoundException;
import com.lz.entity.Blog;
import com.lz.mapper.BlogMapper;
import com.lz.service.BlogService;
import com.lz.service.TagService;
import com.lz.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagService tagService;

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public void saveBlog(Blog blog) {
        if(blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogMapper.saveBlog(blog);
        }else{
            blog.setUpdateTime(new Date());
        }
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public void updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogMapper.updateBlog(blog);
    }

    @Override
    public List<Blog> searchBlog(Blog blog) {
        return blogMapper.searchBlog(blog);
    }





    @Override
    public Integer countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogMapper.findGroupYear();
        Map<String, List<Blog>> map=new HashMap<>();
        for(String year:years){
            map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }

    @Override
    public List<Blog> getBlogByTypeId(Long typeId) {
        return blogMapper.getBlogByTypeId(typeId);
    }

    @Override
    public List<Blog> getBlogByTagId(Long tagId) {
        return blogMapper.getBlogByTagId(tagId);
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Override
    public List<Blog> getRecommendBlog() {
        return blogMapper.getRecommendBlog();
    }

    @Override
    public List<Blog> searchIndexBlog(String query) {
        return blogMapper.searchIndexBlog(query);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog=blogMapper.getDetailedBlog(id);
        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }

        List tags = new ArrayList();
        if(blog.getTagIds()=="" || blog.getTagIds().equals("")){

        }else{
            String[] tagIds=blog.getTagIds().split(",");
            for(String tagId:tagIds){
                Long tagid=Long.parseLong(tagId);
                tags.add(tagService.getTagById(tagid));
            }
            blog.setTags(tags);
        }

        String content=blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogMapper.updateViews(id);
        return blog;
    }


}
