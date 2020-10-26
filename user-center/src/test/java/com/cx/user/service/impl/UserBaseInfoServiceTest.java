package com.cx.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.user.dto.response.UserShowInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.user.UserCenterApplication;
import com.cx.user.dto.request.QueryUserBaseInfoCondition;
import com.cx.user.entity.UserBaseInfo;
import com.cx.user.service.IAPIUserBaseInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class UserBaseInfoServiceTest {

    @Reference
    private IAPIUserBaseInfoService userBaseInfoService;

    @Test
    public void getUserBaseInfoById() {

        UserBaseInfo userBaseInfoById = userBaseInfoService.getUserBaseInfoById(null);
        System.out.println("userBaseInfoById = " + userBaseInfoById);
    }

    @Test
    public void pageQueryUserBaseInfoList() {
        QueryUserBaseInfoCondition condition = new QueryUserBaseInfoCondition();
        condition.setPage(1);
        condition.setPageSize(10);
        Page<UserShowInfo> page = userBaseInfoService.pageQueryUserBaseInfoList(condition);
        System.out.println("page = " + page.getSize());
    }

}