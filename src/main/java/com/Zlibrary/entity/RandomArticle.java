package com.Zlibrary.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "文章")
    private String essay;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "文章创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "文章最新更新时间")
    private LocalDateTime updateTime;

    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除状态，0为没删除，1为删除")
    private Integer deleteStatus;


    public RandomArticle() {
    }
}
