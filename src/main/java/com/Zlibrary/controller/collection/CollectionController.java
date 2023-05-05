package com.Zlibrary.controller.collection;

import com.Zlibrary.entity.Collection;
import com.Zlibrary.entity.RandomArticle;
import com.Zlibrary.entity.User;
import com.Zlibrary.response.ResultData;
import com.Zlibrary.service.CollectionService;
import com.Zlibrary.service.RandomService;
import com.Zlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public CollectionController(CollectionService collectionService, UserService userService, RandomService randomService) {
        this.collectionService = collectionService;
        this.userService = userService;
        this.randomService = randomService;
    }

    @PostMapping("/addCollect")
    public ResultData<?> addCollect(@RequestParam Long userId, @RequestParam Long articleId, @RequestBody Collection collection) {
        User user = userService.getById(userId);

        RandomArticle article = randomService.getById(articleId);
        if (user == null || article == null) {
            return ResultData.fail("User or Article not found");
        }
        collectionService.saveOrUpdate(collection);
        return ResultData.success(collection);
    }

//    @GetMapping("/listCollectsByUser/{userId}")
//    public ResultData<?> listCollectsByUser(@PathVariable Long userId) {
//        List<ArticleCollect> collects = articleCollectRepository.findByUserId(userId);
//        return ResultData.success(collects);
//    }
//
//    @GetMapping("/listCollectsByArticle/{articleId}")
//    public ResultData<?> listCollectsByArticle(@PathVariable Long articleId) {
//        List<ArticleCollect> collects = articleCollectRepository.findByArticleId(articleId);
//        return ResultData.success(collects);
//    }
//
//    @DeleteMapping("/deleteCollect/{id}")
//    public ResultData<?> deleteCollect(@PathVariable Long id) {
//        articleCollectRepository.deleteById(id);
//        return ResultData.success().build();
//
//    }

}

