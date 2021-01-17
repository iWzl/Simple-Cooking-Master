package com.quarks.cooking.pojo.req;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;
/**
 * Profile
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 19:14
 **/
@Data
public class RefreshProfileReq implements HttpResponse {
    private String name;

    private String gender;

    private String aboutMe;

    private String avatar;
}
