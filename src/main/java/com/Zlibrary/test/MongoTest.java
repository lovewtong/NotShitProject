/*package com.Zlibrary.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

*//**
 * @className: MongoTest
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-04-20
 **//*
public class MongoTest {

    @Autowired
    public  MongoTest(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    private final MongoTemplate mongoTemplate;

    @Test
    void MongoTest() {
        Map<String, Object> record = new HashMap<>(4);
        record.put("_id", "1");
        record.put("name", "hello mongo");
        // 插入
        Map<String, Object> result = mongoTemplate.insert(record, "cf_products");
        // 查询
        List<Map> cfProductsMapList = mongoTemplate.findAll(Map.class, "cf_products");
    }
}*/
