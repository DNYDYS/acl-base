package com.tx.base.primary.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tx.base.primary.entity.Role;
import com.tx.base.primary.service.RoleService;
import com.tx.base.utils.R;
import com.tx.base.utils.ResultPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/role")
@Api(tags = "角色管理")
//@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("page")
    @ApiOperation("分页")
    public ResultPage page(@RequestBody Role role) {
        IPage<Role> list = roleService.findpage(role);
        return ResultPage.ok(list);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public R save(@RequestBody Role role) {
        roleService.save(role);
        return R.ok();
    }
}

