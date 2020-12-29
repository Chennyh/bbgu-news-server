package com.chennyh.bbgunews.controller;

import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.UserWxDTO;
import com.chennyh.bbgunews.service.UserWxService;
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
 * @date 2020/12/27 18:40
 * @description 微信接口控制器
 */
@RestController
@Api(tags = "微信用户接口", value = "微信用户相关所有接口")
@RequestMapping("/wx")
public class UseWxController {

    @Resource
    private UserWxService userWxService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "微信用户登录", notes = "成功返回token")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult<Map<String, String>> login(@RequestBody @Valid UserWxDTO userWxDTO) {
        String token = userWxService.login(userWxDTO);
        if (StrUtil.isEmpty(token)) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
