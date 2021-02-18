package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quarks.cooking.mapper.GoodsDao;
import com.quarks.cooking.pojo.bean.Banner;
import com.quarks.cooking.pojo.bean.Goods;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.rsp.BannerRsp;
import com.quarks.cooking.pojo.rsp.GoodsRsp;
import com.quarks.cooking.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Moments
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:59
 **/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public PageOfInfoListRsp<GoodsRsp> fetchPageOfGoodsByType(String type, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Goods> dishesPageInfo = new PageInfo<>(goodsDao.fetchGoodsByType(type));
        return PageOfInfoListRsp.build(dishesPageInfo,goods->{
            GoodsRsp goodsRsp = new GoodsRsp();
            BeanUtils.copyProperties(goods,goodsRsp);
            return goodsRsp;
        });
    }

    @Override
    public BannerRsp fetchAllBanner() {
        List<Banner> bannerList = goodsDao.fetchAllBanner();
        return new BannerRsp(bannerList);
    }
}
