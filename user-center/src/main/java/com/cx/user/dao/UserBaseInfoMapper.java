package com.cx.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.user.dto.request.QueryUserBaseInfoCondition;
import com.cx.user.entity.UserBaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxin
 * @date 2020/6/14
 */
public interface UserBaseInfoMapper extends BaseMapper<UserBaseInfo> {

    /**
     * @Description: 列表查询 可分页 可不分
     * @Author: chenxin
     * @Param: [condition]
     * @Date: 2020/6/14
     */
    List<UserBaseInfo> queryUserBaseInfoList(@Param("condition") QueryUserBaseInfoCondition condition);

    /**
     * @Description: Mybatis plus 分页查询
     * @Author: chenxin
     * @Param: [page, condition]
     * @Date: 2020/6/14
     */
    List<UserBaseInfo> pageQueryUserBaseInfoList(Page<UserBaseInfo> page,
            @Param("condition") QueryUserBaseInfoCondition condition);

}
