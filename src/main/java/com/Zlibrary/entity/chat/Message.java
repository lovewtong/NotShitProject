package com.Zlibrary.entity.chat;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className: Message
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-21
 **/
@ApiModel(value = "chatInformation")
@Data
@TableName("message")
public class Message {

    //消息角色
    private String role;

    //消息内容
    private String content;
}
