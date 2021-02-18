package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quarks.cooking.mapper.DishesDao;
import com.quarks.cooking.pojo.bean.Account;
import com.quarks.cooking.pojo.bean.Course;
import com.quarks.cooking.pojo.bean.Curriculum;
import com.quarks.cooking.pojo.bean.Dishes;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.rsp.CourseRsp;
import com.quarks.cooking.pojo.rsp.DishesRsp;
import com.quarks.cooking.pojo.rsp.GoodsCourseRsp;
import com.quarks.cooking.pojo.rsp.ProfileRsp;
import com.quarks.cooking.service.DishesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Dishes
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:59
 **/
@Service
public class DishesServiceImpl extends ServiceImpl<DishesDao, Dishes> implements DishesService {

    @Resource
    private DishesDao dishesDao;

    @Override
    public PageOfInfoListRsp<DishesRsp> fetchPageOfDailyRecommendDishesList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Dishes> dishesPageInfo = new PageInfo<>(dishesDao.fetchDailyRecommendDishesList());
        return PageOfInfoListRsp.build(dishesPageInfo,dishes->{
            DishesRsp dishesRsp = new DishesRsp();
            BeanUtils.copyProperties(dishes,dishesRsp);
            dishesRsp.setChefProfile(fetchChefProfileByUin(dishes.getChefId()));
            return dishesRsp;
        });
    }

    @Override
    public PageOfInfoListRsp<DishesRsp> fetchPageOfSearchDishesList(String group, String type, String time, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Dishes> dishesPageInfo = new PageInfo<>(dishesDao.fetchSearchDishesList(group,type,time));
        return PageOfInfoListRsp.build(dishesPageInfo,dishes->{
            DishesRsp dishesRsp = new DishesRsp();
            BeanUtils.copyProperties(dishes,dishesRsp);
            dishesRsp.setChefProfile(fetchChefProfileByUin(dishes.getChefId()));
            return dishesRsp;
        });
    }

    @Override
    public PageOfInfoListRsp<DishesRsp> fetchPageOfDishesByDishName(String dishName, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Dishes> dishesPageInfo = new PageInfo<>(dishesDao.fetchPageOfDishesByDishName(dishName));
        return PageOfInfoListRsp.build(dishesPageInfo,dishes->{
            DishesRsp dishesRsp = new DishesRsp();
            BeanUtils.copyProperties(dishes,dishesRsp);
            dishesRsp.setChefProfile(fetchChefProfileByUin(dishes.getChefId()));
            return dishesRsp;
        });
    }

    @Override
    public CourseRsp fetchCourseDetailByCourseId(Integer courseId) {
        CourseRsp courseRsp =new CourseRsp();
        Course course = dishesDao.fetchCourseDetailByCourseId(courseId);
        BeanUtils.copyProperties(course,courseRsp);
        List<Curriculum> curriculumList = dishesDao.fetchCurriculumDetailByCourseId(courseId);
        courseRsp.setCurriculumRspList(new LinkedList<>());
        courseRsp.setChefProfile(fetchChefProfileByUin(course.getChefId()));
        for (Curriculum curriculum : curriculumList) {
            CourseRsp.CurriculumRsp curriculumRsp = new CourseRsp.CurriculumRsp();
            BeanUtils.copyProperties(curriculum,curriculumRsp);
            courseRsp.getCurriculumRspList().add(curriculumRsp);
        }
        return courseRsp;
    }

    @Override
    public GoodsCourseRsp fetchCourseGoodsByChefId(Integer chefId) {
        List<Course> courseList = dishesDao.fetchCourseGoodsByChefId(chefId);
        List<GoodsCourseRsp.Course> courseRspList = new ArrayList<>();
        for (Course course : courseList) {
            GoodsCourseRsp.Course courseRsp = new GoodsCourseRsp.Course();
            BeanUtils.copyProperties(course,courseRsp);
            courseRspList.add(courseRsp);
        }
        GoodsCourseRsp courseRsp  = new GoodsCourseRsp();
        courseRsp.setCourseList(courseRspList);
        return courseRsp;
    }

    public ProfileRsp fetchChefProfileByUin(Integer uid){
        Account account = dishesDao.fetchChefAccountProfileByUid(uid);
        ProfileRsp profileRsp = new ProfileRsp();
        BeanUtils.copyProperties(account,profileRsp);
        return profileRsp;
    }


}
