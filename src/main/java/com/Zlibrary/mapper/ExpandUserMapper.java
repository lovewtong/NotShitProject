package com.Zlibrary.mapper;

import com.Zlibrary.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface ExpandUserMapper extends BaseMapper<User> {

    Integer insertBatchSomeColumn(Collection<User> entityList);
}
