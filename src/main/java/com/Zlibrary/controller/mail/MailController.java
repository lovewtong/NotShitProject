package com.Zlibrary.controller.mail;

import com.Zlibrary.service.MailService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @className: MailController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-04-24
 **/
@Api(tags = "NotShitProject 邮件接口")
@RestController
@RequestMapping("/mailController")
@ApiSupport(author = "sy")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 发送邮件
     * @param email
     * @return
     */

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("email") String email) {
        // 生成随机验证码
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        // 发送邮件
        mailService.sendMail(email, "注册验证码", "您所注册的NotShitProject的验证码是：" + code);
        return code;
    }
}

