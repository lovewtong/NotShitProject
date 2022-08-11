package com.Zlibrary.service.impl;

import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.mapper.RandomMapper;
import com.Zlibrary.service.RandomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-23
 **/
@Service
public class RandomServiceImpl extends ServiceImpl<RandomMapper, RandomArticle> implements RandomService {
    //ServiceImpl<UserMapper, User> implements UserService

}
