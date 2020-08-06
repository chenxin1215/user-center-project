package com.cx.user.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * <新增用户入参>
 *
 * @Author: chenxin
 * @Date: 2020/8/3
 */
public class SaveUserRequest implements Serializable {

    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别：0-女，1-男
     */
    private Integer sex;

    /**
     * 用户电话
     */
    private String userMobile;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 登陆密码
     */
    private String loginPassword;

    /**
     * 用户状态 1-启用 2-禁用
     */
    private Integer userStatus;

    /**
     * 操作人
     */
    private Long operationUserId;

    /**
     * 角色id
     */
    private List<Long> roleIdList;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Long getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Long operationUserId) {
        this.operationUserId = operationUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
