package com.Zlibrary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Zlibrary.mapper")
public class NotShitProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotShitProjectApplication.class, args);
    }

}
