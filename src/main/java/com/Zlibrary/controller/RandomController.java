package com.Zlibrary.controller;

import com.Zlibrary.config.customization.CustomizationProperties;
import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.mapper.RandomMapper;
import com.Zlibrary.response.PageBean;
import com.Zlibrary.response.ResultData;
import com.Zlibrary.service.RandomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @className: RandomController
 * @description: PddBook Controller接口
 * @author: sy
 * @date: 2022-08-05
 **/
@Api(tags = "NotShitProject 阅读文章接口集合")
@RestController
@RequestMapping("/readController")
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
    public RandomArticle DailyRead() {

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

        int num3 = num1 + num2;

        //计算已存在的总数
        long count = randomService.count();

        if (count > num3) {
            return randomService.getById(num3);
        } else {

            long num4 = num3 - count;
            long num5 = num4 % count;

            if (num5 == 0) {
                return randomService.getById(count);
            } else {
                return randomService.getById(num5);
            }

        }

    }

    //根据ID查询详细数据
    @ApiOperation(value = "根据ID读取文章", httpMethod = "GET")
    @GetMapping("/IdRead")
    public RandomArticle IdRead(@RequestParam(value = "id") Integer id) {

        return randomService.getById(id);

    }

    //无条件的分页查询
    @ApiOperation(value = "不带条件的分页查询", httpMethod = "GET")
    @GetMapping("/SelByPage")
    public ResultData SelByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        Page<RandomArticle> page = new Page<>(currentPage, size);
        Page<RandomArticle> randomArticlePage = randomMapper.selectPage(page, null);

        return ResultData.success(new PageBean<>(randomArticlePage.getTotal(), randomArticlePage.getRecords()));
    }

//    public RandomArticle ReadEpub() throws IOException {
//        // 从文件中读取EPUB书籍
//        InputStream epubInputStream = epubAnalysis.class.getResourceAsStream("123.epub");
//        Book book = (new EpubReader()).readEpub(epubInputStream);
//
//        // 获取书籍中的全部章节
//        List<Resource> chapters = book.getContents();
//
//        // 逐一打印每个章节的内容
//        for (nl.siegmann.epublib.domain.Resource chapter : chapters) {
//            System.out.println(new String(chapter.getData()));
//        }
//
//        // 关闭输入流
//        epubInputStream.close();
//
//        return null;
//    }

}
