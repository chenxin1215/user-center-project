package com.cx.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cx.user.dao.UserRoleMapper;
import com.cx.user.entity.UserRole;
import com.cx.user.service.IAPIUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 角色 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysRoleServiceImpl implements IAPIUserRoleService {

    private @Autowired UserRoleMapper roleMapper;

    @Override
    public Set<String> selectRoleByUserId(Long userId) {
        List<UserRole> perms = roleMapper.selectRoleByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (UserRole perm : perms) {
            if (Objects.nonNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
