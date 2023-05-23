package com.Zlibrary.config.customization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @className: CustomizationProperties
 * @description: 自定义配置属性类
 * @author: sy
 * @date: 2022-10-10
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class CustomizationProperties {

    // 自定义初始时间
    @Value("${myCustomConstants.startTime}")
    long startTime;

    // 自定义id
    @Value("${myCustomConstants.id}")
    int id;

    //
//    @Value("${myCustomConstants.local}")
//    String local;

    // 自定义发送邮箱
    @Value("${myCustomConstants.email}")
    String email;
}
