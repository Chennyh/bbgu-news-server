package com.chennyh.bbgunews.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.QueryUserDTO;
import com.chennyh.bbgunews.dto.UserInfoDTO;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.pojo.User;
import com.chennyh.bbgunews.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 22:42
 * @description 用户控制器
 */
@RestController
@Api(tags = "用户接口", value = "用户相关所有接口")
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonResult<User> register(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        User user = userService.register(userLoginDTO);
        if (BeanUtil.isEmpty(user)) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "用户登录", notes = "成功返回token")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult<Map<String, String>> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        if (StrUtil.isEmpty(token)) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取所有用户信息", notes = "成功返回用户列表")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseBody
    public CommonResult<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        if (CollUtil.isEmpty(allUser)) {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(allUser);
    }

    @ApiOperation(value = "获取指定用户信息", notes = "成功返回用户信息JSON")
    @GetMapping
    @ResponseBody
    public CommonResult<UserInfoDTO> getUser(@ModelAttribute QueryUserDTO queryUserDTO) {
        UserInfoDTO userInfoDTO = userService.getUser(queryUserDTO);
        if (BeanUtil.isEmpty(userInfoDTO)) {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(userInfoDTO);
    }

}
