package com.Zlibrary.config.email;

import com.Zlibrary.config.customization.CustomizationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


/**
 * @className: EmailConfig
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-05-05
 **/
public class EmailConfig {

    final CustomizationProperties customizationProperties;
    private final JavaMailSender javaMailSender;


    public EmailConfig(CustomizationProperties customizationProperties, JavaMailSender javaMailSender) {
        this.customizationProperties = customizationProperties;
        this.javaMailSender = javaMailSender;
    }

    /**
     * 发送邮件
     *
     * @param to      接收邮件的邮箱
     * @param subject 邮件主题
     * @param content 邮件正文
     */
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(customizationProperties.getEmail());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }
}
