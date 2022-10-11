package com.Zlibrary.config.customization;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @className: CustomizationProperties
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-10-10
 **/
@Data
@Component
@ConfigurationProperties("time")
public class CustomizationProperties {

    private Date startTime;
}
