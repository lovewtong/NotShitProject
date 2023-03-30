package com.Zlibrary.config.chatGpt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: OpenAiAutoConfiguration
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-22
 **/
@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiAutoConfiguration {

}
