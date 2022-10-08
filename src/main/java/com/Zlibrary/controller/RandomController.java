package com.Zlibrary.controller;

import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.entity.User;
import com.Zlibrary.mapper.RandomMapper;
import com.Zlibrary.service.RandomService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public RandomArticle randomRead() {

        //获得总数
        long count = randomService.count();
        //逻辑有问题
//
//        Random rand = new Random();
//
//        //返回值在范围[0,num) 即[0,num)
//        int n1 = rand.nextInt(i + 1);

        //第二种，random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
        int max = (int) count;
        int min=1;
        Random random = new Random();

        int num = random.nextInt(max)%(max-min+1) + min;

        return randomService.getById(num);
    }

    //根据ID查询详细数据
    @ApiOperation(value = "根据ID读取文章", httpMethod = "GET")
    @GetMapping("/IdRead")
    public RandomArticle IdRead(@PathVariable("id") Integer id) {

        return randomService.getById(id);
    }

    //无条件的分页查询
    @ApiOperation(value = "不带条件的分页查询", httpMethod = "GET")
    @GetMapping("/SelByPage")
    public ResponseEntity<String> SelByPage(@RequestParam(value = "page") Integer currentPage, Integer size) {

        Page<RandomArticle> page = new Page<>(currentPage,size);
        Page<RandomArticle> page1 = randomMapper.selectPage(page, null);

        if (page1 != null) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("false");
    }


}
