package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 21:41
 **/
@Data
public class SelfCollectNumberRsp implements HttpResponse {
    private Integer collectNum;

}
