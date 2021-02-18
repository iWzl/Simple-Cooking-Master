package com.quarks.cooking.controller;

import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.pojo.rsp.BannerRsp;
import com.quarks.cooking.pojo.rsp.GoodsCourseRsp;
import com.quarks.cooking.pojo.rsp.GoodsRsp;
import com.quarks.cooking.service.DishesService;
import com.quarks.cooking.service.GoodsService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * Collection Controller
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 20:57
 **/
@RestController
@RequestMapping(
        value = "/api/goods",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodsController {
    private final GoodsService goodsService;
    private final DishesService dishesService;

    public GoodsController(GoodsService goodsService, DishesService dishesService) {
        this.goodsService = goodsService;
        this.dishesService = dishesService;
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public Msg<PageOfInfoListRsp<GoodsRsp>> fetchPageOfGoodsByType(@Validated @RequestParam("type") String type,
                                                                   @Validated @Min(1) @RequestParam(defaultValue = "1") Integer pageNo,
                                                                   @Validated @Min(1) @Max(100) @RequestParam(defaultValue = "10") Integer pageSize){
        PageOfInfoListRsp<GoodsRsp> pageOfInfoListRsp =  goodsService.fetchPageOfGoodsByType(type,pageNo,pageSize);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping(value = "course",consumes = MediaType.ALL_VALUE)
    public Msg<GoodsCourseRsp> fetchCourseGoodsByChefId(@Validated @RequestParam("chefId") Integer chefId){
        GoodsCourseRsp goodsCourseRsp = dishesService.fetchCourseGoodsByChefId(chefId);
        return Msg.buildSuccessMsg(goodsCourseRsp);
    }


    @Security(RoleEnum.LOGIN)
    @GetMapping(value = "/banner",consumes = MediaType.ALL_VALUE)
    public Msg<BannerRsp> fetchAllBanner(){
        BannerRsp bannerRsp =  goodsService.fetchAllBanner();
        return Msg.buildSuccessMsg(bannerRsp);
    }

}

