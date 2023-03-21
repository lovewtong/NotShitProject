package com.Zlibrary.entity.chat;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @className: CompletionRequest
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-21
 **/
@ApiModel(value = "/chatInformation")
@Data
@TableName("completionRequest")
public class CompletionRequest {

    // chatGpt类型
    private String model;
    // 发送的消息列表
    private List<Message> messages;
    // template:温度，参数从0-2，越低表示越精准，越高表示越广发，回答的内容重复率越低
    private Double template;
    // n:回答条数，一次对话回复的条数
    private Integer n;
    // stream:是否流式处理,就像ChatGPT一样的处理方式,会增量的发送信息
    private Boolean stream;
    // max_tokens:生成的答案允许最大的token数
    private Integer max_token;
    // 对话用户
    private String user;

}
