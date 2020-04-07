package com.uycode.service;

import com.uycode.dao.AdminRolePermissionDAO;
import com.uycode.entity.AdminPermission;
import com.uycode.entity.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDAO.findAllByRid(rid);
    }

//    @Modifying
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDAO.deleteAllByRid(rid);
        for (AdminPermission perm : perms) {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(perm.getId());
            adminRolePermissionDAO.save(rp);
        }
    }
}
