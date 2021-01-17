package com.quarks.cooking.pojo.req;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 用户的登录请求
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 17:23
 **/
@Data
public class AccountLoginReq{
    @Size(min = 4,max = 12)
    private String account;
    @Size(min = 6,max = 18)
    private String password;
}
