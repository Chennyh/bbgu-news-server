package com.chennyh.bbgunews.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.common.CommonPage;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.UserWxDTO;
import com.chennyh.bbgunews.dto.UserWxProfileUpdateDTO;
import com.chennyh.bbgunews.pojo.UserWx;
import com.chennyh.bbgunews.service.UserWxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2020/12/27 18:40
 * @description 微信接口控制器
 */
@RestController
@Api(tags = "微信用户接口", value = "微信用户相关所有接口")
@RequestMapping("/wx")
public class UseWxController {

    @Resource
    private UserWxService userWxService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
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

    @ApiOperation(value = "更新用户信息")
    @PutMapping
    @ResponseBody
    public CommonResult<Integer> update(@RequestBody UserWxProfileUpdateDTO userWxProfileUpdateDTO) {
        int count = userWxService.update(userWxProfileUpdateDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation(value = "获取所有用户信息", notes = "成功返回用户列表，可进行分页查询")
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseBody
    public CommonResult<CommonPage<UserWx>> getUserList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UserWx> users = userWxService.get(keyword, pageSize, pageNum);
        if (CollUtil.isEmpty(users)) {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(CommonPage.restPage(users));
    }

    @ApiOperation(value = "删除指定用户")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> deleteUser(@PathVariable Long id) {
        int count = userWxService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("用户不存在");
    }
}
