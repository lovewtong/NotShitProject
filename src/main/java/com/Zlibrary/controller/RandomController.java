package com.Zlibrary.controller;

import com.Zlibrary.config.customization.CustomizationProperties;
import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.mapper.RandomMapper;
import com.Zlibrary.service.RandomService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @className: RandomController
 * @description: PddBook Controller接口
 * @author: sy
 * @date: 2022-08-05
 **/
@Api(tags = "NotShitProject 接口集合")
@RestController
@RequestMapping("/controller")
@ApiSupport(author = "sy")
public class RandomController {


    private final CustomizationProperties customizationProperties;
    private final RandomMapper randomMapper;
    private final RandomService randomService;

    public RandomController(CustomizationProperties customizationProperties, RandomMapper randomMapper, RandomService randomService) {
        this.customizationProperties = customizationProperties;
        this.randomMapper = randomMapper;
        this.randomService = randomService;
    }

    //插入一条数据
    @ApiOperation(value = "插入一条数据", httpMethod = "POST")
    @PostMapping("/insert")
    public RandomArticle insert(@RequestBody RandomArticle randomArticle) {

        return randomArticle;

    }

    //没数据新增一条，有数据修改一条
    @ApiOperationSupport(includeParameters = {
            "id",
            "title",
            "author",
            "essay"
    })
    @ApiOperation(value = "新增或者修改", httpMethod = "PUT")
    @PutMapping("/saveOrUpdate")
    public RandomArticle saveOrUpdate(@RequestBody RandomArticle randomArticle) {

        randomService.saveOrUpdate(randomArticle);

        return randomArticle;

    }

    //随机阅读
    @ApiOperation(value = "随机根据ID读取文章", httpMethod = "GET")
    @GetMapping("/randomRead")
    public RandomArticle randomRead() {

        //获得总数
        long count = randomService.count();

        //第一种，逻辑有问题
//
//        Random rand = new Random();
//
//        //返回值在范围[0,num) 即[0,num)
//        int n1 = rand.nextInt(i + 1);

        //第二种，random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
        int max = (int) count;
        int min = 1;
        Random random = new Random();

        int num = random.nextInt(max) % (max - min + 1) + min;

        return randomService.getById(num);
    }

    //每日阅读
    @ApiOperation(value = "每日阅读", httpMethod = "GET")
    @GetMapping("/DailyRead")
    public RandomArticle DailyRead() throws ParseException {

        //格式化时间
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //设定初始值
        long time = customizationProperties.getStartTime();
        Date timeDate1 = new Date(time * 1000);   //

        Date timeDate2 = new Date();// 获取当前时间

        long time1 = timeDate1.getTime();
        long time2 = timeDate2.getTime();

        //计算差值，即Id
        int num1 = (int) ((time2 - time1) / (1000 * 60 * 60 * 24));

        int num2 = customizationProperties.getId();

        int id = num1 + num2;

        return randomService.getById(id);
    }

    //根据ID查询详细数据
    @ApiOperation(value = "根据ID读取文章", httpMethod = "GET")
    @GetMapping("/IdRead")
    public RandomArticle IdRead(@RequestParam(value = "id") Integer id) {

        return randomService.getById(id);

    }

//    //无条件的分页查询
//    @ApiOperation(value = "不带条件的分页查询", httpMethod = "GET")
//    @GetMapping("/SelByPage")
//    public ResponseEntity<String> SelByPage(@RequestParam(value = "page") Integer currentPage, Integer size) {
//
//        Page<RandomArticle> page = new Page<>(currentPage, size);
//        Page<RandomArticle> page1 = randomMapper.selectPage(page, null);
//
//        if (page1 != null) {
//            return ResponseEntity.ok("success");
//        }
//        return ResponseEntity.ok("false");
//    }


}
