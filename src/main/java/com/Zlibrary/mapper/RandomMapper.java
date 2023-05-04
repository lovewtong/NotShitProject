package com.Zlibrary.mapper;

import com.Zlibrary.entity.RandomArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @className: RandomMapper
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-09
 **/
public interface RandomMapper extends BaseMapper<RandomArticle> {

    // 分页查询
    List<RandomArticle> findPageArticle(Page<RandomArticle> page);
}
