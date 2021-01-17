package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

/**
 * UploadImageRsp
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 20:05
 **/
@Data
public class UploadImageRsp implements HttpResponse {
    private String imagePath;
}
