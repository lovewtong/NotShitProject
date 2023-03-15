package com.Zlibrary.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: User
 * @description: 用户类
 * @author: sy
 * @date: 2022-08-09
 **/
@ApiModel(value = "userInformation")
@Data
@TableName("user")
public class User {

    @TableId()
    private Integer userId;

    private String password;

    private String firstName;

    private String lastName;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;

    //文章 作者名 日期 类别 标签

}
