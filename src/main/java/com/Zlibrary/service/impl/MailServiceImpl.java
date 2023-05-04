package com.Zlibrary.service.impl;

import com.Zlibrary.config.customization.CustomizationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @className: MailServiceImpl
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-05-04
 **/
@Service
public class MailServiceImpl {

    final CustomizationProperties customizationProperties;
    @Autowired
    private JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender, CustomizationProperties customizationProperties) {
        this.javaMailSender = javaMailSender;
        this.customizationProperties = customizationProperties;
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
