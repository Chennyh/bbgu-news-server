package com.chennyh.bbgunews.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.common.CommonPage;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.UserInfoDTO;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.dto.UserRegisterDTO;
import com.chennyh.bbgunews.dto.UserUpdateDTO;
import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.User;
import com.chennyh.bbgunews.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
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
    public CommonResult<User> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        User user = userService.register(userRegisterDTO);
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

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/refreshToken")
    @ResponseBody
    public CommonResult<Map<String, String>> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/logout")
    @ResponseBody
    public CommonResult<String> logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "获取所有用户信息", notes = "成功返回用户列表，可进行分页查询")
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseBody
    public CommonResult<CommonPage<UserInfoDTO>> getUserList(@RequestParam(value = "keyword", required = false) String keyword,
                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UserInfoDTO> users = userService.getUserList(keyword, pageSize, pageNum);
        if (CollUtil.isEmpty(users)) {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(CommonPage.restPage(users));
    }

    @ApiOperation("获取所有用户")
    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<UserInfoDTO>> listAll() {
        List<UserInfoDTO> userList = userService.listAll();
        if (CollUtil.isNotEmpty(userList)) {
            return CommonResult.success(userList);
        }
        return CommonResult.failed("获取失败");
    }

    @ApiOperation(value = "获取当前登录用户信息", notes = "成功返回用户信息JSON")
    @GetMapping("/info")
    @ResponseBody
    public CommonResult<UserInfoDTO> getCurrentUser(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        User user = userService.getUserByUserName(username);
        UserInfoDTO userInfoDTO = userService.getUser(user);
        if (BeanUtil.isEmpty(userInfoDTO)) {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(userInfoDTO);
    }

    @ApiOperation(value = "获取指定用户信息", notes = "成功返回用户信息JSON")
    @GetMapping("/{id}")
    @ResponseBody
    public CommonResult<UserInfoDTO> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserByUserId(id);
        UserInfoDTO userInfoDTO = userService.getUser(user);
        if (BeanUtil.isEmpty(userInfoDTO)) {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(userInfoDTO);
    }

    @ApiOperation(value = "修改指定用户信息")
    @PutMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> updateUserInfo(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        int count = userService.updateUser(id, userUpdateDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除指定用户")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> deleteUser(@PathVariable Long id) {
        int count = userService.deleteUser(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("用户不存在");
    }

    @ApiOperation("修改帐号状态")
    @PutMapping(value = "/status/{id}")
    @ResponseBody
    public CommonResult<Integer> updateStatus(@PathVariable Long id, @RequestParam Boolean status) {
        int count = userService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改指定用户角色信息")
    @PutMapping("/roles/{id}")
    @ResponseBody
    public CommonResult<Integer> updateUserRoles(@PathVariable Long id, @RequestParam List<Long> roleIds) {
        int count = userService.updateRoles(id, roleIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取指定用户角色信息")
    @GetMapping("/roles/{id}")
    @ResponseBody
    public CommonResult<List<Role>> getUserRoles(@PathVariable Long id) {
        List<Role> roles = userService.getRoles(id);
        if (CollUtil.isNotEmpty(roles)) {
            return CommonResult.success(roles);
        }
        return CommonResult.failed();
    }


}
