package com.Zlibrary.service.impl;

import com.Zlibrary.entity.Collection;
import com.Zlibrary.mapper.CollectionMapper;
import com.Zlibrary.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @className: CollectionServiceImpl
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-05-05
 **/
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
}
