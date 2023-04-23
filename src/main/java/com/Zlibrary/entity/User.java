package com.Zlibrary.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer userId;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓")
    private String firstName;

    @ApiModelProperty(value = "名")
    private String lastName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    // 性别，0为不愿透露，1为男，2为女
    @ApiModelProperty(value = "性别，0为不愿透露，1为男，2为女")
    private Integer gender;

    //年龄
    @ApiModelProperty(value = "年龄")
    private Integer age;

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
