package com.Zlibrary.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: RandomArticle
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-09
 **/
@ApiModel(value = "userInformation")
@Data
@TableName("article")
public class RandomArticle {

    @TableId()
    private Integer id;

    private String title;

    private String author;

    private String essay;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;


    public RandomArticle() {
    }
}
