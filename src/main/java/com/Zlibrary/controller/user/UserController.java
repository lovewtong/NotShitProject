package com.Zlibrary.controller.user;

import com.Zlibrary.entity.User;
import com.Zlibrary.mapper.UserMapper;
import com.Zlibrary.response.PageBean;
import com.Zlibrary.response.ResultData;
import com.Zlibrary.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @className: UserController
 * @description: 用户接口controller
 * @author: sy
 * @date: 2023-04-23
 **/
@Api(tags = "NotShitProject 用户接口集合")
@RestController
@RequestMapping("/userController")
@ApiSupport(author = "sy")
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;
    private final RedisTemplate<String, String> redisTemplate;

    public UserController(UserService userService, UserMapper userMapper, RedisTemplate<String, String> redisTemplate) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    // 判断用户是否被删除
    private boolean isUserDeleted(User user) {
        return user.getDeleteStatus() == 1;
    }

    /**
     * 查询所有用户信息,无条件分页查询，防止数据量较大情况下，查询时间过长
     *
     * @param currentPage page
     * @param pageSize    size
     * @return ResultData 用户list
     */
    @ApiOperation(value = "查询所有用户信息", httpMethod = "GET")
    @GetMapping("/findAll")
    public ResultData SelByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize) {

        // 判断参数是否为空
        if (pageSize == null || currentPage == null) {
            return ResultData.fail("页码为0");
        }

        // 校验分页参数是否合法
        if (pageSize < 1 || pageSize > 100) {
            return ResultData.fail("页码须在1到100之间");
        }
        if (currentPage < 1) {
            return ResultData.fail("页码须大于1");
        }

        // 校验
        // 只查询未删除的用户和已激活的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_status", 0)
                .eq("activation_status", 1);

        // 分页查询
        Page<User> page = new Page<>(currentPage, pageSize);
        // 条件queryWrapper
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        PageBean<User> userPageBean = new PageBean<>(userPage.getTotal(), userPage.getRecords());
        return ResultData.success(userPageBean);

    }

    /**
     * 根据ID查询用户信息
     *
     * @param id 用户ID
     * @return ResultData code,msg,用户Data
     */
    @ApiOperation(value = "根据ID查询用户信息", httpMethod = "GET")
    @GetMapping("/findById")
    public ResultData findById(@RequestParam(value = "userId") Integer id) {

        User userId = userService.getById(id);

        // 判断用户是否存在，existsById方法封装在UserService中
        if (userId == null) {
            return ResultData.fail(404, "该用户不存在");
        }
        if (userId.getDeleteStatus() == 1) {
            return ResultData.fail(404, "该用户不存在");
        }
        User byId = userService.getById(id);
        return ResultData.success(byId);

    }

    /**
     * 新增用户信息，需要经过邮箱的验证
     *
     * @param user 用户详细信息
     * @return ResultData code,msg
     */
    @ApiOperation(value = "新增用户信息", httpMethod = "POST")
    @PostMapping("/insert")
    public ResultData insert(@RequestBody User user) {

        // 判断邮箱是否重复,注册邮箱是唯一凭证，existsByEmail方法封装在UserService中
        boolean email = userService.existsByEmail(user.getEmail());
        if (email) {
            return ResultData.fail(500, "邮箱重复");
        } else {
            userService.save(user);
            return ResultData.success(200, "添加成功");
        }

    }

    /**
     * 根据ID修改用户信息
     *
     * @param user 用户需要修改的详细信息
     * @return ResultData code,msg
     */
    @ApiOperation(value = "根据ID修改用户信息", httpMethod = "PUT")
    @PutMapping("/updateById")
    public ResultData updateById(@RequestBody User user) {

        // 获取用户ID
        Integer id = user.getUserId();
        // 获取用户状态，看是否被删除
        User oldUser = userService.getById(id);
        // 查询用户的邮箱信息
        String email = oldUser.getEmail();

        if (isUserDeleted(oldUser)) {
            userService.updateById(user);
            return ResultData.success(200, "修改失败");
        } else {
            if (!user.getEmail().equals(email)) {
                return ResultData.fail(400, "新旧邮箱不能一致");
            }
            return ResultData.fail(400, "该用户不存在");
        }
    }

    /**
     * 换绑邮箱
     *
     * @param id       用户id
     * @param oldEmail 需要被修改的邮箱
     * @param newEmail 修改后的邮箱
     * @return ResultData code,msg
     */
    @ApiOperation(value = "换绑邮箱", httpMethod = "PUT")
    @PutMapping("/updateEmail")
    public ResultData updateEmail(@RequestParam Integer id,
                                  @RequestParam String oldEmail,
                                  @RequestParam String newEmail,
                                  @RequestParam ("email") String email,
                                  @RequestParam ("code") String code) {

        // 获取用户
        User userId = userService.getById(id);
        // 获取用户状态，看是否被删除
        if (userId == null) {
            return ResultData.fail(400, "该用户不存在");
        }
        if (isUserDeleted(userId)) {
            return ResultData.fail(400, "该用户不存在");
        }
        // 根据旧邮箱查询用户信息
        User oldUser = userService.getOne(new QueryWrapper<User>().eq("email", oldEmail));
        if (oldUser == null || oldUser.getDeleteStatus() == 1) {
            return ResultData.fail(400, "邮箱不存在或已被删除");
        }
        // 根据新邮箱查询用户信息
        User newUser = userService.getOne(new QueryWrapper<User>().eq("email", newEmail));
        if (newUser != null) {
            return ResultData.fail(400, "新邮箱已被占用");
        }

        // redis的key值
        String prefix = "email_";
        String key = prefix + email;
        // 读取redis上的code值
        String codeInRedis = redisTemplate.opsForValue().get(key);
        if (codeInRedis != null && codeInRedis.equals(code)) {
            // 验证码通过后删除key
            redisTemplate.delete(key);
            oldUser.setEmail(newEmail);
            userService.updateById(oldUser);
            return ResultData.success("换绑成功");
        } else {
            return ResultData.fail("验证错误");
        }
    }


    /**
     * 根据ID删除用户信息，逻辑删除
     *
     * @param id 用户id
     * @return ResultData code,msg
     */
    @ApiOperation(value = "根据ID删除用户信息", httpMethod = "DELETE")
    @DeleteMapping("/deleteById")
    public ResultData deleteById(Integer id) {

        User byId = userService.getById(id);
        if (isUserDeleted(byId)) {
            return ResultData.fail(400, "删除失败");
        }
        userService.removeById(id);
        return ResultData.success(200, "删除成功");

    }


}
