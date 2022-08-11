package com.Zlibrary.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: knife4jConfig
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-11
 **/
@Data
@Component
@ConfigurationProperties("knife4j")
public class knife4jConfig {

    private String enable;

    private String documents;

    private String setting;

    private String basic;

    @Data
    @Component
    @ConfigurationProperties("jwt")
    public static class JwtConfigProperties {

        private String header;

        private String tokenPrefix;

        private String secret;

        private String expireTime;
    }
}


