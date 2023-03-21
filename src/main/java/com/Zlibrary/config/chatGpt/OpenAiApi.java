package com.Zlibrary.config.chatGpt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @className: OpenAiApi
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-21
 **/
@Slf4j
@Component
public class OpenAiApi {

    @Value("${open.ai.url}")
    private String url;

    @Value("${open.ai.token}")
    private String token;

//    private static final MultiThreadedHttpConnectionManagerCONNECTION_MANAGER= new MultiThreadedHttpConnectionManager();

}
