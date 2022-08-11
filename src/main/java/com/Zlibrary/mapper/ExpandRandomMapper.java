package com.Zlibrary.mapper;

import com.Zlibrary.entity.RandomArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface ExpandRandomMapper extends BaseMapper<RandomArticle> {

    Integer insertBatchSomeColumn(Collection<RandomArticle> entityList);
}
