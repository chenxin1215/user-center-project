package com.cx.user.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 〈用户信息 用于展示〉
 *
 * @author chenxin
 * @date 2020/6/14
 */
public class UserShowInfo implements Serializable {

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户电话
     */
    private String userMobile;

    /**
     * 用户状态 1-启用 2-禁用
     */
    private Integer userStatus;

    /**
     * 用户状态
     */
    private String userStatusStr;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatusStr() {
        return userStatusStr;
    }

    public void setUserStatusStr(String userStatusStr) {
        this.userStatusStr = userStatusStr;
    }
}