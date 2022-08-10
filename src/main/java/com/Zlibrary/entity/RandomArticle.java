package com.Zlibrary.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

/**
 * @className: RandomArticle
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-08-09
 **/
public class RandomArticle {

    @TableId(type = IdType.AUTO)
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


}
