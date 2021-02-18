package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.bean.Banner;
import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

import java.util.List;

/**
 * Banner
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-18 14:10
 **/
@Data
public class BannerRsp implements HttpResponse {
    private List<Banner> bannerList;

    public BannerRsp(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }
}
