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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private RandomMapper randomMapper;

    @Autowired
    private RandomService randomService;


    //插入一条数据
    @ApiOperation(value = "插入一条数据", httpMethod = "POST")
    @PostMapping("/insert")
    @ApiImplicitParam(name = "ArticleParam", type = "body", dataTypeClass = RandomArticle.class, required = true)
    public ResponseEntity<String> insert(@RequestBody RandomArticle randomArticle) {

        if (randomService.save(randomArticle)) {

            String essay = randomArticle.getEssay();
            String s = numFormat(essay);

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

    private static String numFormat(String line) {
        String pattern = "[一二两三四五六七八九十○零百0-9１２３４５６７８９０]{1,12}、";
        Pattern p = Pattern.compile(pattern);

        // 获取 matcher 对象
        Matcher m = p.matcher(line);
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            m.appendReplacement(sb,"\n"+m.group(0));
        }
        m.appendTail(sb);
        return sb.toString() ;
    }
}
