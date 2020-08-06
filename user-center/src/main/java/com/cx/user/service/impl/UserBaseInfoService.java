package com.cx.user.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.user.dao.RelUserRoleMapper;
import com.cx.user.dao.UserBaseInfoMapper;
import com.cx.user.dto.request.SaveUserRequest;
import com.cx.user.dto.request.QueryUserBaseInfoCondition;
import com.cx.user.dto.response.UserShowInfo;
import com.cx.user.entity.RelUserRole;
import com.cx.user.entity.UserBaseInfo;
import com.cx.user.enums.UserStatusEnum;
import com.cx.user.service.IAPIUserBaseInfoService;
import com.sun.xml.internal.ws.server.ServerRtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author chenxin
 * @date 2020/6/14
 */
@Service
public class UserBaseInfoService implements IAPIUserBaseInfoService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserBaseInfoService.class);

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    @Autowired
    private RelUserRoleMapper relUserRoleMapper;

    @Override
    @Transactional
    public Long addUser(SaveUserRequest request) {

        // 检查数据
        checkAddUserRequest(request);

        // 新增用户
        UserBaseInfo addUser = new UserBaseInfo();
        BeanUtils.copyProperties(addUser, request);
        addUser.setCreateUserId(request.getOperationUserId());
        addUser.setUpdateUserId(request.getOperationUserId());
        userBaseInfoMapper.insert(addUser);
        Long userId = addUser.getUserId();

        // 处理角色关系
        for (Long roleId : request.getRoleIdList()) {
            RelUserRole rel = new RelUserRole();
            rel.setRoleId(roleId);
            rel.setUserId(userId);
            relUserRoleMapper.insert(rel);
        }

        return userId;
    }

    /**
     * 检查新增用户数据
     */
    private void checkAddUserRequest(SaveUserRequest request) {
        String loginName = request.getLoginName();
        String userMobile = request.getUserMobile();
        String loginPassword = request.getLoginPassword();
        Long operationUserId = request.getOperationUserId();
        List<Long> roleIdList = request.getRoleIdList();

        Assert.notNull(loginName, "登陆名不能为空");
        Assert.notNull(userMobile, "电话不能为空");
        Assert.notNull(loginPassword, "密码不能为空");
        Assert.notNull(operationUserId, "操作人不能为空");
        Assert.notEmpty(roleIdList, "用户角色不能为空");

        Integer count = userBaseInfoMapper
            .selectCount(new LambdaQueryWrapper<UserBaseInfo>().eq(UserBaseInfo::getLoginName, loginName));
        if (count > 0) {
            LOGGER.error("登陆名已存在！");
            throw new ServerRtException("登陆名已存在");
        }

        count = userBaseInfoMapper
            .selectCount(new LambdaQueryWrapper<UserBaseInfo>().eq(UserBaseInfo::getUserMobile, userMobile));
        if (count > 0) {
            LOGGER.error("手机号已注册！");
            throw new ServerRtException("手机号已注册");
        }

        String nickname = request.getNickname();
        if (!StringUtils.isBlank(nickname)) {
            count = userBaseInfoMapper
                .selectCount(new LambdaQueryWrapper<UserBaseInfo>().eq(UserBaseInfo::getNickname, nickname));
            if (count > 0) {
                LOGGER.error("昵称已存在！");
                throw new ServerRtException("昵称已存在");
            }
        } else {
            request.setNickname(loginName);
        }
    }

    @Override
    public UserBaseInfo getUserBaseInfoById(Long userId) {
        // if (userId == null) {
        // LOGGER.warn("getUserBaseInfoById 查询id为空！");
        // return null;
        // }

        return userBaseInfoMapper.selectById(userId);
    }

    @Override
    public Map<Long, UserBaseInfo> BatchGetUserBaseInfoById(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return new HashMap<>();
        }
        LambdaQueryWrapper<UserBaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(UserBaseInfo::getUserId, userIdList);
        List<UserBaseInfo> userBaseInfoList = userBaseInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(userBaseInfoList)) {
            Map<Long, UserBaseInfo> userBaseInfoMap =
                userBaseInfoList.stream().collect(Collectors.toMap(UserBaseInfo::getUserId, val -> val));
            return userBaseInfoMap;
        }
        return new HashMap<>();
    }

    @Override
    public UserBaseInfo getUserBaseInfoByLoginName(String userName) {
        System.out.println("### getUserBaseInfoByLoginName start userName:" + userName);
        if (StringUtils.isBlank(userName)) {
            LOGGER.warn("getUserBaseInfoByLoginName 查询条件为空！");
            return null;
        }
        LambdaQueryWrapper<UserBaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        // 登陆名
        queryWrapper.eq(UserBaseInfo::getLoginName, userName);

        System.out.println("### getUserBaseInfoByLoginName end");
        return userBaseInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public UserBaseInfo getUserBaseInfoByMobile(String mobile) {
        System.out.println("### getUserBaseInfoByMobile start mobile:" + mobile);
        if (StringUtils.isBlank(mobile)) {
            LOGGER.warn("getUserBaseInfoByMobile 查询条件为空！");
            return null;
        }
        LambdaQueryWrapper<UserBaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        // 手机号
        queryWrapper.eq(UserBaseInfo::getUserMobile, mobile);

        System.out.println("### getUserBaseInfoByMobile end");
        return userBaseInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UserBaseInfo> queryUserBaseInfoList(QueryUserBaseInfoCondition condition) {
        return userBaseInfoMapper.queryUserBaseInfoList(condition);
    }

    @Override
    public Page<UserShowInfo> pageQueryUserBaseInfoList(QueryUserBaseInfoCondition condition) {
        Page<UserShowInfo> result = new Page<>();

        Page<UserBaseInfo> page = new Page<>(condition.getPage(), condition.getPageSize());
        List<UserBaseInfo> userBaseInfoList = userBaseInfoMapper.pageQueryUserBaseInfoList(page, condition);
        result.setTotal(userBaseInfoList.size());

        // 组装数据
        if (CollectionUtils.isNotEmpty(userBaseInfoList)) {
            List<UserShowInfo> dataList = new ArrayList<>();
            for (UserBaseInfo userBaseInfo : userBaseInfoList) {
                UserShowInfo data = new UserShowInfo();
                dataList.add(data);
                BeanUtils.copyProperties(userBaseInfo, data);

                Integer userStatus = userBaseInfo.getUserStatus();
                String userStatusStr =
                    Optional.ofNullable(userStatus).map(val -> UserStatusEnum.parse(val).toString()).orElse(null);
                data.setUserStatusStr(userStatusStr);
            }
            result.setRecords(dataList);
        }

        return result;
    }

    @Override
    @Transactional
    public void updateUserBaseInfo(SaveUserRequest request) {
        Long userId = request.getUserId();
        Assert.notNull(userId, "用户id不能为空");
        List<Long> roleIdList = request.getRoleIdList();
        Assert.notEmpty(roleIdList, "角色列表不能为空");

        UserBaseInfo userBaseInfo = userBaseInfoMapper.selectById(userId);
        if (userBaseInfo == null) {
            throw new ServerRtException("用户不存在！");
        }

        String nickname = request.getNickname();
        if (!StringUtils.isBlank(nickname) && !userBaseInfo.getNickname().equals(nickname)) {
            Integer count = userBaseInfoMapper
                .selectCount(new LambdaQueryWrapper<UserBaseInfo>().eq(UserBaseInfo::getNickname, nickname));
            if (count > 0) {
                throw new ServerRtException("昵称已存在");
            }
        }

        String userMobile = request.getUserMobile();
        if (!StringUtils.isBlank(userMobile) && !userBaseInfo.getUserMobile().equals(userMobile)) {
            Integer count = userBaseInfoMapper
                .selectCount(new LambdaQueryWrapper<UserBaseInfo>().eq(UserBaseInfo::getUserMobile, userMobile));
            if (count > 0) {
                throw new ServerRtException("手机号已存在");
            }
        }

        UserBaseInfo updateUser = new UserBaseInfo();
        BeanUtils.copyProperties(request, updateUser);
        updateUser.setUpdateUserId(request.getOperationUserId());
        userBaseInfoMapper.updateById(updateUser);

        relUserRoleMapper.delete(new LambdaQueryWrapper<RelUserRole>().eq(RelUserRole::getUserId, userId));
        for (Long roleId : roleIdList) {
            RelUserRole rel = new RelUserRole();
            rel.setRoleId(roleId);
            rel.setUserId(userId);
            relUserRoleMapper.insert(rel);
        }

    }

    @Override
    public void resetPassword(String encode, Long userId) {
        Objects.requireNonNull(userId, "userId cannot be null");
        Objects.requireNonNull(encode, "password cannot be null");

        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserId(userId);
        userBaseInfo.setLoginPassword(encode);
        userBaseInfoMapper.updateById(userBaseInfo);
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO
    }
}