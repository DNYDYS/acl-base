package com.tx.base.primary.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tx.base.primary.entity.User;
import com.tx.base.utils.R;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 用户接口
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/user")
//@CrossOrigin
@Api(tags = "用户管理")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private com.tx.base.primary.service.UserService userService;

    @Autowired
    private com.tx.base.primary.service.RoleService roleService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username", userQueryVo.getUsername());
        }

        IPage<User> pageModel = userService.page(pageParam, wrapper);
        return R.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public R save(@RequestBody User user) {
        BCryptPasswordEncoder bp = new BCryptPasswordEncoder();
        user.setPassword(bp.encode(user.getPassword()));
        // 这里要判断用户名是否重复
        userService.save(user);
        return R.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public R toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return R.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public R doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId, roleId);
        return R.ok();
    }

    /**
     * 获取验证码接口
     * @param response
     */
    @ApiOperation("获取验证码")
    @GetMapping("/getVerificationCode")
    public void getVerificationCode(ServletResponse response) {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(220, 80, 5, 6);
        //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        //captcha.write("d:/shear.png");
        //验证图形验证码的有效性，返回boolean值
        log.info("当前验证码为：" + captcha.getCode());
        //  验证码放到redis中 key:value
        redisTemplate.opsForValue().set("123", captcha.getCode());
        captcha.verify("1234");
        try {
            //  写入验证码到流中
            captcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("获取easyCaptcha")
    @GetMapping("/getEasyCaptcha")
    public void easyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        //SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        //// 设置字体
        //specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        //// 设置类型，纯数字、纯字母、字母数字混合
        //specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

        // 算术验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        //captcha.setLen(3);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        captcha.text();  // 获取运算的结果：5
        System.out.println(captcha.text());


        // 验证码存入session
        //request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

        // 输出图片流
        try {
            captcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

