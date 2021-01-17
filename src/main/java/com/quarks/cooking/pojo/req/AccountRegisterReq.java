package com.quarks.cooking.pojo.req;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Account Register
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 19:10
 **/
@Data
public class AccountRegisterReq {
    @Size(min = 4,max = 12)
    private String account;
    @Size(min = 2,max = 18)
    private String name;
    @Size(min = 6,max = 18)
    private String password;

    private String gender;
    @Size(min = 8)
    private String avatar;

}
