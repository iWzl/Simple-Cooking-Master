package com.quarks.cooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quarks.cooking.pojo.bean.Goods;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.rsp.BannerRsp;
import com.quarks.cooking.pojo.rsp.GoodsRsp;

/**
 * Goods
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:56
 **/
public interface GoodsService extends IService<Goods> {
    PageOfInfoListRsp<GoodsRsp> fetchPageOfGoodsByType(String type, Integer pageNo, Integer pageSize);

    BannerRsp fetchAllBanner();

    GoodsRsp fetchOneOfGoodsByGoodsId(Long goodsId);
}
