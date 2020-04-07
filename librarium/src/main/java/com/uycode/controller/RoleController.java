package com.uycode.controller;

import com.uycode.dao.AdminRoleDAO;
import com.uycode.entity.AdminRole;
import com.uycode.result.Result;
import com.uycode.result.ResultFactory;
import com.uycode.service.AdminPermissionService;
import com.uycode.service.AdminRoleMenuService;
import com.uycode.service.AdminRolePermissionService;
import com.uycode.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * Role controller.
 *
 * @author Evan
 * @date 2019/11
 */
@RestController
public class RoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    @GetMapping("/api/admin/role")
    public Result listRoles(){
        return ResultFactory.buildSuccessResult(adminRoleService.list());
    }

    @PutMapping("/api/admin/role/status")
    public Result updateRoleStatus(@RequestBody AdminRole requestRole) {
        AdminRole adminRole = adminRoleService.updateRoleStatus(requestRole);
        String message = "用户" + adminRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @DeleteMapping("/api/admin/role/deleteRole/{roleId}")
    public Result deleteRoleById(@PathVariable("roleId") Integer rid){
        adminRoleService.deleteRoleById(rid);
        return ResultFactory.buildSuccessResult(rid);
    }

    @PutMapping("/api/admin/role")
    public Result editRole(@RequestBody AdminRole requestRole) {
        adminRoleService.addOrUpdate(requestRole);
        adminRolePermissionService.savePermChanges(requestRole.getId(), requestRole.getPerms());
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }


    @PostMapping("/api/admin/role")
    public Result addRole(@RequestBody AdminRole requestRole) {
        if (adminRoleService.editRole(requestRole)) {
            return ResultFactory.buildSuccessResult("修改用户成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，修改失败");
        }
    }

    @GetMapping("/api/admin/role/perm")
    public Result listPerms() {
        return ResultFactory.buildSuccessResult(adminPermissionService.list());
    }

    @PutMapping("/api/admin/role/menu")
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody LinkedHashMap menusIds) {
        if(adminRoleMenuService.updateRoleMenu(rid, menusIds)) {
            return ResultFactory.buildSuccessResult("更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }
}
