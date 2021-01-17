package com.quarks.cooking.pojo.rsp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AccountLoginRsp
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 17:49
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountLoginRsp extends ProfileRsp {


    private String token;
}
