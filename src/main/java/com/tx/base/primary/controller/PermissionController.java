package com.tx.base.primary.controller;

import com.tx.base.primary.entity.Permission;
import com.tx.base.primary.service.PermissionService;
import com.tx.base.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限 菜单管理
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/permission")
@Api(tags = "权限接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<Permission> list = permissionService.queryAllMenu();
        return Result.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId, permissionId);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.ok(list);
    }
}

