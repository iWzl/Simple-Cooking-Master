package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

/**
 * CollectBuildRsp
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 21:27
 **/
@Data
public class CollectBuildRsp implements HttpResponse {
    boolean hasCollect;
}
