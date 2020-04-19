package com.lz.service.impl;

import com.lz.entity.User;
import com.lz.mapper.UserMapper;
import com.lz.service.UserService;
import com.lz.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public User login(String username, String password) {
        return userMapper.login(username, MD5Utils.code(password));
    }
}
