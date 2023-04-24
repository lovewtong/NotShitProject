package com.Zlibrary.config.customization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @className: CustomizationProperties
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-10-10
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class CustomizationProperties {

    @Value("${myCustomConstants.startTime}")
    long startTime;

    @Value("${myCustomConstants.id}")
    int id;

    @Value("${myCustomConstants.local}")
    String local;

    @Value("${myCustomConstants.email}")
    String email;
}
