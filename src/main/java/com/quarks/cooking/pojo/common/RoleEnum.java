package com.quarks.cooking.pojo.common;

/**
 * 身份权限的枚举
 *
 * @author Leonardo iWzl
 **/
public enum RoleEnum {
    // 登录态
    LOGIN("LOGIN"),
    //登录权限
    ANONYMOUS("anonymous");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleEnum getRole(String role) {
        if(role == null || "".equals(role)){
            return ANONYMOUS;
        }
        for(RoleEnum roleEnum:values()){
            if(roleEnum.getRole().equals(role)){
                return roleEnum;
            }
        }
        return ANONYMOUS;
    }
}
