package com.lz.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lize
 */

@Data
public class Tag {

    /**编号*/
    private Long id;

    /**标签名*/
    private String name;

    /**级联关系*/
    private List<Blog> blogs = new ArrayList<>();

}
