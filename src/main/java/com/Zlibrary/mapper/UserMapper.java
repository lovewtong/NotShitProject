package com.Zlibrary.mapper;

import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @className: UserMapper
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-09
 **/
public interface UserMapper extends BaseMapper<User> {

    // 分页查询
    List<User> findPageUser(Page<User> page);
}
