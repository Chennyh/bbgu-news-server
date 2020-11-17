package com.chennyh.bbgunews.controller;

import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.pojo.User;
import com.chennyh.bbgunews.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
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
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "用户登录",notes = "成功返回token")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult<Map<String, String>> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

}
