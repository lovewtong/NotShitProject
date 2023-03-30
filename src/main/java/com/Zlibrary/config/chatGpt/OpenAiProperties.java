package com.Zlibrary.config.chatGpt;

import com.Zlibrary.controller.OpenAiUtils;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className: OpenAiProperties
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-22
 **/
@Data
@ConfigurationProperties(prefix = "openai")
public class OpenAiProperties implements InitializingBean {

    String token;

    Integer timeout;

    @Override
    public void afterPropertiesSet() throws Exception {
        OpenAiUtils.OPENAPI_TOKEN = token;
        OpenAiUtils.TIMEOUT = timeout;
    }
}
