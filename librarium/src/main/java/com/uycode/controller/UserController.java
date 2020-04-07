package com.uycode.controller;

import com.uycode.result.Result;
import com.uycode.result.ResultFactory;
import com.uycode.entity.User;
import com.uycode.service.AdminUserRoleService;
import com.uycode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User controller.
 *
 * @author Evan
 * @date 2019/11
 */

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }

    @DeleteMapping("/api/admin/user/deleteUserBy/{id}")
    public Result deleteUserById(@PathVariable("id") Integer id){
        if(userService.deleteUserById(id)){
            return ResultFactory.buildSuccessResult("删除成功");
        }
        return ResultFactory.buildFailResult("删除失败");
    }

    @GetMapping("/api/admin/user1/{username}")
    public Result listUser(@PathVariable("username") String username){
        return ResultFactory.buildSuccessResult(userService.getUserInfo(username));
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody User requestUser) {
        if (userService.updateUserStatus(requestUser)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody User requestUser) {
        if (userService.resetPassword(requestUser)) {
            return ResultFactory.buildSuccessResult("重置密码成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，重置失败");
        }
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody User requestUser) {
        if(userService.editUser(requestUser)) {
            return ResultFactory.buildSuccessResult("修改用户信息成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，修改失败");
        }
    }
}
