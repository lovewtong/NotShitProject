package com.Zlibrary.controller.article;

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

    /**
     * 新增一篇文章
     *
     * @param randomArticle 用户详细信息
     * @return ResultData code,msg,data:randomArticle
     */
    // 插入一篇文章
    @ApiOperation(value = "插入一条数据", httpMethod = "POST")
    @PostMapping("/insert")
    public ResultData insert(@RequestBody RandomArticle randomArticle) {

        randomMapper.insert(randomArticle);
        return ResultData.success(randomArticle);

    }

    /**
     * 没数据新增一条，有数据修改一条
     *
     * @param randomArticle 用户详细信息
     * @return ResultData code,msg,data:randomArticle
     */
    @ApiOperation(value = "新增或者修改", httpMethod = "PUT")
    @PutMapping("/saveOrUpdate")
    public ResultData saveOrUpdate(@RequestBody RandomArticle randomArticle) {

        randomService.saveOrUpdate(randomArticle);

        return ResultData.success(randomArticle);

    }

    /**
     * 随机阅读文章
     * 获得文章总数，再随机生成1-count之间的随机数字
     *
     * @return ResultData code,msg,data:randomArticle
     */
    @ApiOperation(value = "随机根据ID读取文章", httpMethod = "GET")
    @GetMapping("/randomRead")
    public ResultData randomRead() {

        // 获得总数
        long count = randomService.count();

        // random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
        int max = (int) count;
        int min = 1;
        Random random = new Random();

        int num = random.nextInt(max) % (max - min + 1) + min;

        return ResultData.success("这是第" + num + "篇文章");
    }

    /**
     * 每日阅读
     * 从一个设置好的初始值开始计算,超过最大文章数就进入循环
     *
     * @return ResultData code,msg,data:randomArticle
     */
    @ApiOperation(value = "每日阅读", httpMethod = "GET")
    @GetMapping("/DailyRead")
    public ResultData DailyRead() {

        // 格式化时间
        // SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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
            randomService.getById(num3);
            return ResultData.success(randomService.getById(num3));
        } else {

            long num4 = num3 - count;
            long num5 = num4 % count;

            if (num5 == 0) {
                randomService.getById(count);
                return ResultData.success(randomService.getById(count));
            } else {
                randomService.getById(num5);
                return ResultData.success(randomService.getById(num5));
            }

        }

    }

    /**
     * 根据ID查询详细数据
     *
     * @param id 用户id
     * @return ResultData code,msg,data:randomArticle
     */
    @ApiOperation(value = "根据ID读取文章", httpMethod = "GET")
    @GetMapping("/IdRead")
    public ResultData IdRead(@RequestParam(value = "id") Integer id) {

        randomService.getById(id);

        return ResultData.success(randomService.getById(id));

    }

    /**
     * 无条件的分页查询
     *
     * @param currentPage page
     * @param size size
     * @return ResultData code,msg,data:randomArticle list
     */
    @ApiOperation(value = "不带条件的分页查询", httpMethod = "GET")
    @GetMapping("/SelByPage")
    public ResultData SelByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        Page<RandomArticle> page = new Page<>(currentPage, size);
        Page<RandomArticle> randomArticlePage = randomMapper.selectPage(page, null);

        return ResultData.success(new PageBean<>(randomArticlePage.getTotal(), randomArticlePage.getRecords()));
    }


}
