package com.quarks.cooking.annotation;

import com.quarks.cooking.pojo.common.RoleEnum;

import java.lang.annotation.*;

/**
 * 用户安全权限相关配置
 * 用户标识是否需要安全校验和相关配置操作
 *
 * @author Leonardo iWzl
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {
    // 用户所拥有的权限身份
    RoleEnum[] value() default {RoleEnum.ANONYMOUS};
}
