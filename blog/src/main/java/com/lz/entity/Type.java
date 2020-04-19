package com.lz.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Type {

    /**编号*/
    private Long id;

    /**分类名*/
    private String name;

    /**级联关系*/
    private List<Blog> blogs = new ArrayList<>();

}
