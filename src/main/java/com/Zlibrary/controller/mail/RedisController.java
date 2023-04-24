package com.Zlibrary.controller.mail;


import com.Zlibrary.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: RedisController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-04-24
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/set/{key}/{value}")
    public String setValue(@PathVariable String key, @PathVariable Object value) {
        redisService.setValue(key, value);
        return "Set " + key + " to " + value.toString() + " successfully.";
    }

    @GetMapping("/{key}")
    public String getValue(@PathVariable String key) {
        Object value = redisService.getValue(key);
        if (value != null) {
            return "Key: " + key + ", Value: " + value.toString();
        } else {
            return "Key not found.";
        }
    }

    @DeleteMapping("/{key}")
    public String deleteKey(@PathVariable String key) {
        redisService.deleteValue(key);
        return "Delete " + key + " successfully.";
    }
}

