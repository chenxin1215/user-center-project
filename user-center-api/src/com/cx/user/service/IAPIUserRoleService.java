package com.cx.user.service;

import java.util.Set;

/**
 *
 * @author chenxin
 * @date 2020/7/19
 */
public interface IAPIUserRoleService {

    Set<String> selectRoleByUserId(Long userId);

}