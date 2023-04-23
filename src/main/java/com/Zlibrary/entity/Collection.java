package com.Zlibrary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: Collection
 * @description: 用户收藏表，作为文章和用户的中间表
 * @author: sy
 * @date: 2023-04-23
 **/
@ApiModel(value = "userInformation")
@Data

public class Collection {

    // 收藏ID自增
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    // 用户ID
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    // 文章ID
    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

    // 收藏时间,即创建时间
    @ApiModelProperty(value = "收藏时间")
    private String collectionTime;

    // 收藏状态
    @ApiModelProperty(value = "收藏状态，0为没收藏，1为收藏，当第一次收藏，即创建收藏时，状态为1")
    private Integer collectionStatus;

    public Collection() {
    }

}
