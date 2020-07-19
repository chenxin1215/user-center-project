package com.cx.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.user.entity.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<UserRole> selectRoleByUserId(Long userId);

}