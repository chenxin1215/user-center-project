package com.cx.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.user.dto.request.QueryUserBaseInfoCondition;
import com.cx.user.dto.response.UserShowInfo;
import com.cx.user.entity.UserBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * @author chenxin
 * @date 2020/6/14
 */
public interface IAPIUserBaseInfoService {

    /**
     * @Description: 新增用户
     * @Author: chenxin
     * @Param: [userBaseInfo]
     * @Date: 2020/6/14
     */
    Long addUser(UserBaseInfo userBaseInfo);

    /**
     * @Description: 根据用户id获取用户
     * @Author: chenxin
     * @Param: [userId]
     * @Date: 2020/6/14
     */
    UserBaseInfo getUserBaseInfoById(Long userId);

    /**
     * @Description: 根据用户id批量获取用户
     * @Author: chenxin
     * @Param: [userIdList]
     * @Date: 2020/6/14
     */
    Map<Long,UserBaseInfo> BatchGetUserBaseInfoById(List<Long> userIdList);

    /**
     * @Description: 根据登陆名获取用户
     * @Author: chenxin
     * @Param: [userName]
     * @Date: 2020/6/14
     */
    UserBaseInfo getUserBaseInfoByLoginName(String userName);

    /**
     * @Description: 根据手机号获取用户
     * @Author: chenxin
     * @Param: [userName]
     * @Date: 2020/6/14
     */
    UserBaseInfo getUserBaseInfoByMobile(String mobile);

    /**
     * @Description: 查询用户列表
     * @Author: chenxin
     * @Param: [condition]
     * @Date: 2020/6/14
     */
    List<UserBaseInfo> queryUserBaseInfoList(QueryUserBaseInfoCondition condition);

    /**
     * @Description: 查询用户列表 分页
     * @Author: chenxin
     * @Param: [condition]
     * @Date: 2020/6/14
     */
    Page<UserShowInfo> pageQueryUserBaseInfoList(QueryUserBaseInfoCondition condition);

    /**
     * @Description: 修改用户基本信息
     * @Author: chenxin
     * @Param: [userBaseInfo]
     * @Date: 2020/6/14
     */
    Long updateUserBaseInfo(UserBaseInfo userBaseInfo);

    /**
     * @Description: 重置密码
     * @Author: chenxin
     * @Param: [encode, userId]
     * @Date: 2020/6/14
     */
    void resetPassword(String encode, Long userId);

    /**
     * @Description: 删除用户, 状态设为禁用, 角色关系保留
     * @Author: chenxin
     * @Param: [userId]
     * @Date: 2020/6/14
     */
    void deleteUser(Long userId);

}
