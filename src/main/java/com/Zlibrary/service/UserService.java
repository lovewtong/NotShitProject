package com.Zlibrary.service;

import com.Zlibrary.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @className: UserService
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-23
 **/
public interface UserService extends IService<User> {

    // 判断用户id是否存在
    default boolean existsById(Integer id) {
        return this.getById(id) != null;
    }

//    default String getEmailById(Integer id) {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.select("email").eq("id", id);
//        List<Object> objList = this.listObjs(wrapper);
//        if (CollectionUtils.isEmpty(objList)) {
//            return null;
//        }
//        return objList.get(0).toString();
//    }

    // 判断用户email是否存在
    default boolean existsByEmail(String email) {
        return this.findByEmail(email) != null;
    }

    // 查找用户email
    default User findByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        return this.getOne(wrapper);
    }

    // 判断用户是否存在，基于用户id存在，账号已激活的情况下再判断的三次判断
    default User getByIdAliveStatus(Integer id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("delete_status", 0);
        return this.getOne(wrapper);
    }

    // 判断用户是否存在，基于用户id存在的情况下再判断的二次判断
    default User getByIdStatus(Integer id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("activation_status", 1);
        return this.getOne(wrapper);
    }


}
