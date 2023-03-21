package com.Zlibrary.entity.chat;

import lombok.Data;

/**
 * @className: CompletionChoice
 * @description: chatGpt返回的数据
 * @author: sy
 * @date: 2023-03-21
 **/
@Data
public class CompletionChoice {

    Integer index;

    Message message;

    String finishReason;
}
