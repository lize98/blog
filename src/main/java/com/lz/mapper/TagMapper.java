package com.lz.mapper;

import com.lz.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TagMapper {
    public List<Tag> getAllTag();
    public Tag getTagById(@Param("id") Long id);
    public Tag getTagByName(@Param("name") String name);
    public void saveTag(Tag tag);
    public void updateTag(Tag tag);
    public void deleteTag(Long id);
}
