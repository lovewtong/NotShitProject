package com.Zlibrary.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: ChatGptController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-21
 **/
@RestController
@RequestMapping("/chatGpt")
@ApiSupport(author = "sy")
public class ChatGptController {

    Map<String,String> headers = new HashMap<>();

    headers.put("Content-Type","application/json;charset=UTF-8");


}
