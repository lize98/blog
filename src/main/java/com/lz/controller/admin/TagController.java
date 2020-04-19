package com.lz.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.entity.Tag;
import com.lz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //获取分页后的标签
    @GetMapping("/tags")
    public String tags(@RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Tag> tags=tagService.getAllTag();
        PageInfo pageInfo=new PageInfo<>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //跳转到新增页面
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag()); //给新增页面传递一个object对象
        return "admin/tags-input";
    }

    //跳转到编辑页面,并将要修改的数据回显
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        //首先通过编号获取到标签
        Tag tag=tagService.getTagById(id);

        //回显数据
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    //新增标签操作
    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes, Model model) {
        //通过标签名获取标签
        String name=tag.getName();
        Tag t= tagService.getTagByName(name);
        //判断标签是否已经存在
        if (t == null ) {
            tagService.saveTag(tag);
            attributes.addFlashAttribute("message", "新增成功");
            return "redirect:/admin/tags";
        } else {
            model.addAttribute("message", "新增失败，标签名重复");
            return "admin/tags-input";
        }
    }

    //编辑标签
    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, RedirectAttributes attributes,Model model) {
        //通过标签名获取标签
        String name=tag.getName();
        Tag t= tagService.getTagByName(name);
        //判断标签是否已经存在
        if (t == null ) {
            tagService.updateTag(tag);
            attributes.addFlashAttribute("message", "更新成功");
            return "redirect:/admin/tags";
        } else {
            model.addAttribute("message", "更新失败，标签名重复");
            return "admin/tags-input";
        }
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
