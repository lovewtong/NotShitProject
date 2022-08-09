package com.Zlibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Zlibrary.entity.User;
import com.Zlibrary.mapper.UserMapper;
import com.Zlibrary.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-23
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    //ServiceImpl<UserMapper, User> implements UserService

}
