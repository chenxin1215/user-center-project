package com.cx.user.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.user.dao.UserBaseInfoMapper;
import com.cx.user.dto.request.QueryUserBaseInfoCondition;
import com.cx.user.dto.response.UserShowInfo;
import com.cx.user.entity.UserBaseInfo;
import com.cx.user.enums.UserStatusEnum;
import com.cx.user.service.IAPIUserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public Long addUser(UserBaseInfo userBaseInfo) {
        // TODO 检查用户名是否唯一

        // TODO 需要检查手机号与邮箱的格式与唯一性

        userBaseInfoMapper.insert(userBaseInfo);

        return userBaseInfo.getUserId();
    }

    @Override
    public UserBaseInfo getUserBaseInfoById(Long userId) {
        if (userId == null) {
            LOGGER.warn("getUserBaseInfoById 查询id为空！");
            return null;
        }

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
    public Long updateUserBaseInfo(UserBaseInfo userBaseInfo) {
        return null;
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

    }
}