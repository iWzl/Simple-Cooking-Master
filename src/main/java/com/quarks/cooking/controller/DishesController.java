package com.quarks.cooking.controller;

import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.pojo.rsp.DishesRsp;
import com.quarks.cooking.pojo.rsp.ProfileRsp;
import com.quarks.cooking.service.DishesService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Msg<PageOfInfoListRsp<DishesRsp>> fetchAccountProfile(@Validated @RequestParam("uid")Integer uid){
        return Msg.buildSuccessMsg("sa",new PageOfInfoListRsp<>());
    }

}
