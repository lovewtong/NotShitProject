package com.Zlibrary.controller;

import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.mapper.RandomMapper;
import com.Zlibrary.service.RandomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @className: RandomController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-05
 **/
@Api(tags = "NotShitProject 接口集合")
@RestController
@RequestMapping("/controller")
public class RandomController {


    @Autowired
    private RandomMapper randomMapper;

    @Autowired
    private RandomService randomService;


    //插入一条数据
    @ApiOperation(value = "插入一条数据", httpMethod = "POST")
    @PostMapping("/insert")
    @ApiImplicitParam(name = "ArticleParam", type = "body", dataTypeClass = RandomArticle.class, required = true)
    public ResponseEntity<String> insert(@RequestBody RandomArticle randomArticle) {

        if (randomService.save(randomArticle)) {

            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("false");
        }

    }

    //没数据新增一条，有数据修改一条
    @ApiOperation(value = "新增或者修改", httpMethod = "PUT")
    @PutMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody RandomArticle randomArticle) {

        return randomService.saveOrUpdate(randomArticle);

    }

    //随机阅读
    @ApiOperation(value = "随机根据ID读取文章", httpMethod = "GET")
    @GetMapping("/randomRead")
    @ApiImplicitParam(name = "ArticleParam", type = "body", dataTypeClass = RandomArticle.class, required = true)
    public RandomArticle randomRead() {

        long count = randomService.count();
        int i = (int)count;
        int num = i+1;

        Random rand=new Random();

        //返回值在范围[0,100) 即[0,99
        int n1=rand.nextInt(num);

        return randomService.getById(n1);
    }
}
