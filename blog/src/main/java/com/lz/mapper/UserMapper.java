package com.lz.mapper;

import com.lz.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    public User login(@Param("username") String username,@Param("password") String password);
}
