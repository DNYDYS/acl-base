package com.tx.base.primary.controller;

import com.alibaba.fastjson.JSONObject;
import com.tx.base.primary.service.IndexService;
import com.tx.base.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("index")
//@CrossOrigin
@Api(tags = "首页接口")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 获取登录用户信息
     */
    @GetMapping("info")
    @ApiOperation("获取登录用户信息")
    public R info() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    @ApiOperation("获取菜单")
    public R getMenu() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    /**
     * 注销登录
     * @return
     */
    @ApiOperation("注销登录")
    @PostMapping("logout")
    public R logout() {
        System.out.println("logout执行了。。。");
        return R.ok();
    }

    /**
     * 当前接口不需要认证
     * @return
     */
    @ApiOperation("无需认证接口")
    @GetMapping("unAuth")
    public R unAuth() {
        System.out.println("unAuth。。。");
        return R.ok().message("当前接口没有认证，不需要token");
    }
}
