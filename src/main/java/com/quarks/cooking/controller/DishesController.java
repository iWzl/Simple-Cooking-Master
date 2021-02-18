package com.quarks.cooking.controller;

import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.pojo.rsp.CourseRsp;
import com.quarks.cooking.pojo.rsp.DishesRsp;
import com.quarks.cooking.service.DishesService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Dishes Controller
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-17 21:44
 **/

@RestController
@RequestMapping(
        value = "/api/dishes",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class DishesController {

    private final DishesService dishesService;

    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }


    @GetMapping(value = "/recommend")
    @Security(RoleEnum.LOGIN)
    public Msg<PageOfInfoListRsp<DishesRsp>> fetchPageOfDailyRecommendDishesList(@Validated @Min(1) @RequestParam Integer pageNo,
                                                                 @Validated @Min(1) @Max(100) @RequestParam Integer pageSize){
        PageOfInfoListRsp<DishesRsp> pageOfInfoListRsp = dishesService.fetchPageOfDailyRecommendDishesList(pageNo,pageSize);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }


    @GetMapping(value = "/filter")
    @Security(RoleEnum.LOGIN)
    public Msg<PageOfInfoListRsp<DishesRsp>> fetchPageOfSearchDishesList(@RequestParam String group,
                                                                         @RequestParam String type,
                                                                         @RequestParam String time,
                                                                         @Validated @Min(1) @RequestParam(defaultValue = "1") Integer pageNo,
                                                                         @Validated @Min(1) @Max(100) @RequestParam(defaultValue = "10") Integer pageSize){
        PageOfInfoListRsp<DishesRsp> pageOfInfoListRsp = dishesService.fetchPageOfSearchDishesList(group,type,time,pageNo,pageSize);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }

    @GetMapping(value = "/search")
    @Security(RoleEnum.LOGIN)
    public Msg<PageOfInfoListRsp<DishesRsp>> fetchPageOfDishesByDishName( @NotBlank @RequestParam String dishName,
                                                                                  @Validated @Min(1) @RequestParam Integer pageNo,
                                                                                  @Validated @Min(1) @Max(100) @RequestParam Integer pageSize){
        PageOfInfoListRsp<DishesRsp> pageOfInfoListRsp = dishesService.fetchPageOfDishesByDishName(dishName,pageNo,pageSize);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }

    @GetMapping(value = "/course")
    @Security(RoleEnum.LOGIN)
    public Msg<CourseRsp> fetchCourseDetailByCourseId(@NotBlank @RequestParam Integer courseId){
        CourseRsp pageOfInfoListRsp = dishesService.fetchCourseDetailByCourseId(courseId);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }

}
