package com.Zlibrary.controller;

import com.Zlibrary.mapper.RandomMapper;
import com.Zlibrary.service.RandomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RandomController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-05
 **/
@Api(tags = "mybatisPlus CRUD")
@RestController
@RequestMapping("/controller")
public class RandomController {
    @Autowired
    private RandomService randomService;

    @Autowired
    private RandomMapper randomMapper;


}
