package com.uycode.controller;

import com.uycode.result.Result;
import com.uycode.result.ResultFactory;
import com.uycode.entity.User;
import com.uycode.service.AdminUserRoleService;
import com.uycode.service.ProfileService;
import com.uycode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserProfile controller.
 *
 * @author UyCode
 * @date 2019/11
 */

@RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/api/user/{username}")
    public Result listUser(@PathVariable("username") String username){
        return ResultFactory.buildSuccessResult(profileService.getUserInfo(username));
    }

    @PutMapping("/api/user/status")
    public Result updateUserStatus(@RequestBody User requestUser) {
        if (profileService.updateUserStatus(requestUser)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

    @PutMapping("/api/user/password")
    public Result resetPassword(@RequestBody User requestUser) {
        if (profileService.resetPassword(requestUser)) {
            return ResultFactory.buildSuccessResult("重置密码成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，重置失败");
        }
    }

    @PutMapping("/api/userEdit")
    public Result editUser(@RequestBody User requestUser) {
        if(profileService.editUserProfile(requestUser)) {
            return ResultFactory.buildSuccessResult("修改用户信息成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，修改失败");
        }
    }
}
