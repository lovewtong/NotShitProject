package com.Zlibrary.controller.collection;

import com.Zlibrary.entity.Collection;
import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.entity.User;
import com.Zlibrary.mapper.CollectionMapper;
import com.Zlibrary.response.ResultData;
import com.Zlibrary.service.CollectionService;
import com.Zlibrary.service.RandomService;
import com.Zlibrary.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: CollectionController
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-04-28
 **/
@RestController
@RequestMapping("/articleCollect")
public class CollectionController {


    private final CollectionService collectionService;
    private final UserService userService;
    private final RandomService randomService;
    private final CollectionMapper collectionMapper;

    @Autowired
    public CollectionController(CollectionService collectionService, UserService userService, RandomService randomService, CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.userService = userService;
        this.randomService = randomService;
        this.collectionMapper = collectionMapper;
    }

    /**
     * 新增收藏
     *
     * @param collection    收藏类实体
     * @return ResultData code,msg
     */
    @PostMapping("/addCollect")
    public ResultData addCollect(@RequestBody Collection collection) {

        Integer userId = collection.getUserId();
        Integer articleId = collection.getArticleId();

        if (userId == null || articleId == null) {
            return ResultData.fail("User or Article not found");
        }

        collectionService.saveOrUpdate(collection);
        return ResultData.success(collection);
    }

    /**
     * 根据用户userId查询用户的所有收藏
     *
     * @param userId 用户id
     * @return ResultData code,msg
     */
    @ApiOperation(value = "根据用户ID查询收藏", httpMethod = "GET")
    @GetMapping("/getCollection/{userId}")
    public ResultData getCollection(@PathVariable Integer userId) {

        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getUserId, userId)
                .select(Collection::getArticleId);
        List<Collection> collections = collectionMapper.selectList(queryWrapper);
        return ResultData.success(collections);

    }

    /**
     * 根据用户userId删除收藏文章
     *
     * @param userId 用户id
     * @return ResultData code,msg
     */
    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable("id") Integer userId) {
        collectionMapper.deleteById(userId);
    }


}

