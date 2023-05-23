package com.Zlibrary.service;

import com.Zlibrary.config.customization.CustomizationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @className: EmailService
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-05-06
 **/
@Service
public class EmailService {

    // 静态javaMailSender实例对象
    private static JavaMailSender javaMailSender;
    // 静态自定义配置属性类
    private static CustomizationProperties customizationProperties;

    public EmailService(JavaMailSender javaMailSender, CustomizationProperties customizationProperties) {
        EmailService.javaMailSender = javaMailSender;
        EmailService.customizationProperties = customizationProperties;
    }

    /**
     * 发送邮件
     *
     * @param to      接收邮件的邮箱
     * @param subject 邮件主题
     * @param content 邮件正文
     */
    public static void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(customizationProperties.getEmail());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }

}
