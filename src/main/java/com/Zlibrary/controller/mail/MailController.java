package com.Zlibrary.controller.mail;

import com.Zlibrary.config.customization.CustomizationProperties;
import com.Zlibrary.config.email.EmailConfig;
import com.Zlibrary.response.ResultData;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @className: MailController
 * @description: 邮箱接口, 负责邮件的发送与邮件的验证
 * @author: sy
 * @date: 2023-04-24
 **/
@Api(tags = "NotShitProject 邮件接口")
@RestController
@RequestMapping("/mailController")
@ApiSupport(author = "sy")
public class MailController {


    private final RedisTemplate<String, String> redisTemplate;

    public MailController(RedisTemplate<String, String> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    /**
     * 发送邮件
     *
     * @param email 注册的邮件地址
     * @return ResultData code,msg,data:验证码
     */

    @PostMapping("/sendMail")
    public ResultData sendMail(@RequestParam("email") String email) {

        EmailConfig emailConfig = new EmailConfig(new CustomizationProperties(), new JavaMailSenderImpl());
//        JavaMailSender mailSender = new JavaMailSenderImpl();
//        CustomizationProperties customizationProperties = new CustomizationProperties();
//        EmailConfig mailService = new EmailConfig(customizationProperties, mailSender);
        // 区分Redis的key命名
        String prefix = "email_";
        String key = prefix + email;

        // 生成随机验证码
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        // 将验证码存入redis中
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        // 发送邮件
        emailConfig.sendMail(email, "注册验证码", "您所注册的NotShitProject的验证码是：" + code);
        return ResultData.success(200, "key is" + key, "code is" + code);

    }

    /**
     * 验证邮箱
     *
     * @param email 注册的邮箱地址
     * @param code  待验证的验证码
     * @return ResultData msg
     */
    @PostMapping("/verifyMail")
    public ResultData verifyMail(@RequestParam("email") String email, @RequestParam("code") String code) {

        // redis的key值
        String prefix = "email_";
        String key = prefix + email;
        // 读取redis上的code值
        String codeInRedis = redisTemplate.opsForValue().get(key);
        if (codeInRedis != null && codeInRedis.equals(code)) {
            // 验证码通过后删除key
            redisTemplate.delete(key);
            return ResultData.success("已删除key" + key);
        } else {
            return ResultData.fail("验证码错误");
        }

    }
}

