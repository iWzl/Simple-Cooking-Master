package com.quarks.cooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quarks.cooking.pojo.bean.Dishes;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.rsp.CourseRsp;
import com.quarks.cooking.pojo.rsp.DishesRsp;

/**
 * DishesService
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-17 21:36
 **/
public interface DishesService extends IService<Dishes> {
    PageOfInfoListRsp<DishesRsp> fetchPageOfDailyRecommendDishesList(Integer pageNo, Integer pageSize);

    PageOfInfoListRsp<DishesRsp> fetchPageOfSearchDishesList(String group, String type, String time,
                                                             Integer pageNo, Integer pageSize);

    PageOfInfoListRsp<DishesRsp> fetchPageOfDishesByDishName(String dishName, Integer pageNo, Integer pageSize);

    CourseRsp fetchCourseDetailByCourseId(Integer courseId);
}
