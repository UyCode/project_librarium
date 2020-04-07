package com.uycode.service;

import com.uycode.dao.UserDAO;
import com.uycode.entity.AdminRole;
import com.uycode.entity.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */
@Service
public class ProfileService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    /*public List<User> list() {
        List<User> users =  userDAO.list();
        List<AdminRole> roles;
        for (User user : users) {
            roles = adminRoleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }*/

    public List<User> getUserInfo(String username){
        User user = userDAO.findByUsername(username);
        List<AdminRole> roles;
        roles = adminRoleService.listRolesByUser(username);
        user.setRoles(roles);
        List<User> users = new LinkedList<>();
        users.add(user);
        return users;
    }


    public boolean updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        try {
            userDAO.save(userInDB);
        } catch (IllegalArgumentException e) {
            return false;
        } return true;
    }

    public boolean resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        try {
            userDAO.save(userInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean editUserProfile(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        try {
            userDAO.save(userInDB);
            /*adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());*/
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
