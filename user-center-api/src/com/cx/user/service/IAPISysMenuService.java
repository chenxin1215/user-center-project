package com.cx.user.service;

import com.cx.user.entity.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * 〈〉
 *
 * @author chenxin
 * @date 2020/7/19
 */
public interface IAPISysMenuService {

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

}
