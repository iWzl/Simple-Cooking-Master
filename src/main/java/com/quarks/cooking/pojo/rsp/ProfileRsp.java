package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

/**
 * Profile
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 19:14
 **/
@Data
public class ProfileRsp implements HttpResponse {
    private Integer uid;

    private String account;

    private String name;

    private String gender;

    private String aboutMe;

    private String avatar;

    private Integer status;

    private Long createTime;

    private Long updateTime;

    private Long lastLoginTime;
}
