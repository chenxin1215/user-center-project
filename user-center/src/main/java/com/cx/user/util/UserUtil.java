package com.cx.user.util;

public class UserUtil {
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
