package com.lz.service;

import com.lz.entity.Blog;
import com.lz.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagService {
    public List<Tag> getAllTag();
    public Tag getTagById(Long id);
    public Tag getTagByName(String name);
    public void saveTag(Tag tag);
    public void updateTag(Tag tag);
    public void deleteTag(Long id);

}
