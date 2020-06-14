package com.cx.user.dto.request;

import com.cx.user.commom.PageCondition;

/**
 * 〈用户信息查询条件〉
 *
 * @author chenxin
 * @date 2020/6/14
 */
public class QueryUserBaseInfoCondition extends PageCondition {

    /**
     * 关键字
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}