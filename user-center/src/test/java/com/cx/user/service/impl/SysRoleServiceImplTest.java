package com.cx.user.service.impl;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cx.user.service.IAPIUserRoleService;
import com.cx.utils.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceImplTest {

    @Autowired
    private IAPIUserRoleService userRoleService;

    @Test
    public void selectRoleByUserId() {
        Set<String> strings = userRoleService.selectRoleByUserId(2L);
        System.out.println("JsonUtil.toString(strings) = " + JsonUtil.toString(strings));

    }
}