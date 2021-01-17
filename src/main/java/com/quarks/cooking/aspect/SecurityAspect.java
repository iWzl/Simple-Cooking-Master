package com.quarks.cooking.aspect;

import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.bean.Account;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.ResultCodeEnum;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.service.AccountService;
import com.quarks.cooking.utils.HttpUtil;
import com.quarks.cooking.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 安全拦截相关切面
 *
 * @author Leonardo iWzl
 **/
@Slf4j
@Aspect
@Component
@Order(1)
public class SecurityAspect {
    private final static String TOKEN_LOGGING = "X-TOKEN >> uid:{} token:{}";

    @Resource
    AccountService accountService;

    @Pointcut("execution(* com.quarks.cooking.controller..*.*(..))")
    public void pointCut() {
    }


    @Around(value = "pointCut()&&@annotation(com.quarks.cooking.annotation.Security)")
    public Object roleAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 从原有的方法调用中获取用户安全拦截的注解
        Class<?> classTarget=proceedingJoinPoint.getTarget().getClass();
        Class<?>[] par=((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        String methodName=proceedingJoinPoint.getSignature().getName();
        Method objMethod=classTarget.getMethod(methodName, par);
        // 从方法中获取到注解 并判断注解中写的参数
        Security security = objMethod.getAnnotation(Security.class);
        // 判断方法需不需要判断token的正确性
        if(null == security){
            return proceedingJoinPoint.proceed();
        }
        for (RoleEnum role : security.value()) {
            if (role != RoleEnum.ANONYMOUS) {
                String token = HttpUtil.getToken();
                Integer uid = HttpUtil.getUid();
                if (null == token || "".equals(token) || uid == 0) {
                    // 获取用户的Token失败,返回提示用户需要首先登录
                    return Msg.buildUnauthorizedFailedMsg(ResultCodeEnum.NOT_LOGIN, "please log in first");
                }
                log.debug(TOKEN_LOGGING, uid, token);
                Account account = accountService.getById(uid);
                if (null == account || account.getUid() == 0) {
                    // 用户不存在 返回用户不存在
                    return Msg.buildUnauthorizedFailedMsg(ResultCodeEnum.ERROR_ACCOUNT, "user does not exist");
                }
                if (!JWTUtil.verify(token, account.getPassword())) {
                    return Msg.buildUnauthorizedFailedMsg(ResultCodeEnum.ERROR_TOKEN, "token error");
                }
            }
            return proceedingJoinPoint.proceed();
        }
        // 所有的校验完成 用户没有访问的权限的匹配 直接返回
        return Msg.buildUnauthorizedFailedMsg(ResultCodeEnum.UNAUTHORIZED, "permission denied");
    }
}
