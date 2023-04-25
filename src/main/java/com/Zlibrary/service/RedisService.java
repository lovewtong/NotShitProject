package com.Zlibrary.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @className: RedisService
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-04-24
 **/
@Service
public class RedisService {

    private RedisTemplate<String,Object> redisTemplate;

    public RedisService(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        redisTemplate.opsForValue().get(key);
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }


}
