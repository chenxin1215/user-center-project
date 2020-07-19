package com.cx.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cx.user.service.IAPISysMenuService;
import com.cx.user.service.IAPIUserPermissionService;
import com.cx.user.service.IAPIUserRoleService;
import com.cx.user.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserPermissionServiceImpl implements IAPIUserPermissionService {

    @Autowired
    private IAPIUserRoleService roleService;

    @Autowired
    private IAPISysMenuService menuService;

    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserUtil.isAdmin(userId)) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRoleByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserUtil.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
}
