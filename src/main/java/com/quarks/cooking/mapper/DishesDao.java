package com.quarks.cooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quarks.cooking.pojo.bean.Account;
import com.quarks.cooking.pojo.bean.Course;
import com.quarks.cooking.pojo.bean.Curriculum;
import com.quarks.cooking.pojo.bean.Dishes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dishes
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-17 21:34
 **/
public interface DishesDao extends BaseMapper<Dishes> {
    List<Dishes> fetchDailyRecommendDishesList();

    List<Dishes> fetchSearchDishesList(@Param("group") String group,
                                       @Param("type") String type,
                                       @Param("time") String time);

    List<Dishes> fetchPageOfDishesByDishName(@Param("dishName") String dishName);

    Account fetchChefAccountProfileByUid(@Param("uid") Integer uid);


    Course fetchCourseDetailByCourseId(@Param("courseId") Integer courseId);

    List<Course> fetchCourseGoodsByChefId(@Param("chefId") Integer chefId);


    List<Curriculum> fetchCurriculumDetailByCourseId(@Param("courseId") Integer courseId);

}
