package com.lz.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lize
 */

@Data
public class Blog {
    /**编号*/
    private Long id;

    /**标题*/
    private String title;

    /**内容*/
    private String content;

    /**首部图片地址*/
    private String firstPicture;

    /**原创、翻译、转载*/
    private String flag;

    /**浏览次数*/
    private Integer views;

    /**开启赞赏*/
    private boolean appreciation;

    /**开启分享声名*/
    private boolean shareStatement;

    /**开启评论*/
    private boolean commentabled;

    /**是否发布*/
    private boolean published;

    /**是否推荐*/
    private boolean recommend;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**文章描述*/
    private String description;

    /**用于关联*/
    private Long typeId;
    private Long userId;

    /**获取多个标签编号*/
    private String tagIds;

    //下面是级联关系
    private Type type;
    private List<Tag> tags=new ArrayList<>();
    private List<Comment> comments=new ArrayList<>();
    private User user;

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串：1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

}
