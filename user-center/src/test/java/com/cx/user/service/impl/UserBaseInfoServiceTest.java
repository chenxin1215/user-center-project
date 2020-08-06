package com.cx.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.user.entity.UserBaseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cx.user.UserCenterApplication;
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
}