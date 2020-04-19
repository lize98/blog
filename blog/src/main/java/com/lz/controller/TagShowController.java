package com.lz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.entity.Blog;
import com.lz.entity.Tag;
import com.lz.service.BlogService;
import com.lz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum, @PathVariable("id") Long id, Model model) {
        //获取所有标签
        List<Tag> tags=tagService.getAllTag();

        //博客首页点击more进来的
        if (id == -1) {
            //自动选择第一个分标签，显示其下的博客
            id = tags.get(0).getId();
        }

        PageHelper.startPage(pageNum,5);
        List<Blog> blogs=blogService.getBlogByTagId(id);
        PageInfo pageInfo=new PageInfo<>(blogs);

        for(Tag tag:tags){
            tag.setBlogs(blogService.getBlogByTagId(tag.getId()));
        }

        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
