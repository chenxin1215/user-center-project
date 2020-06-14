package com.cx.user.enums;

/**
 * 〈用户状态〉
 *
 * @author chenxin
 * @date 2020/6/14
 */
public enum UserStatusEnum {

    enable(1, "启用"), disable(2, "禁用");

    private int value;

    private String desc;

    UserStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserStatusEnum parse(int value) {
        for (UserStatusEnum type : UserStatusEnum.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("无法解析的状态值：" + value);
    }

    public int value() {
        return value;
    }

    public String toString() {
        return this.desc;
    }

}
