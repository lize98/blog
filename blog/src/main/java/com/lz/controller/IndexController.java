package com.lz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.entity.Blog;
import com.lz.entity.Tag;
import com.lz.entity.Type;
import com.lz.service.BlogService;
import com.lz.service.TagService;
import com.lz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lize
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,6);
        //获取所有分类
        List<Type> types=typeService.getAllType();
        for (Type type:types){
            type.setBlogs(blogService.getBlogByTypeId(type.getId()));
        }

        PageHelper.startPage(pageNum,6);
        //获取所有标签
        List<Tag> tags=tagService.getAllTag();
        for (Tag tag:tags){
            tag.setBlogs(blogService.getBlogByTagId(tag.getId()));
        }

        PageHelper.startPage(pageNum,6);
        //前台获取博客
        List<Blog> blogs=blogService.getIndexBlog();
        PageInfo pageInfo=new PageInfo<>(blogs);

        PageHelper.startPage(pageNum,6);
        //前台获取推荐博客
        List<Blog> recommendBlogs=blogService.getRecommendBlog();

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        model.addAttribute("recommendBlogs", recommendBlogs);
        return "index";
    }


    @PostMapping("/search")
    public String search(@RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
                         @RequestParam String query, Model model) {
        PageHelper.startPage(pageNum,6);
        List<Blog> blogs=blogService.searchIndexBlog(query);
        PageInfo pageInfo=new PageInfo<>(blogs);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        Blog blog=blogService.getDetailedBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        PageHelper.startPage(1,2);
        //前台获取推荐博客
        List<Blog> recommendBlogs=blogService.getRecommendBlog();
        model.addAttribute("newblogs", recommendBlogs);
        return "_fragments :: newblogList";
    }

}
