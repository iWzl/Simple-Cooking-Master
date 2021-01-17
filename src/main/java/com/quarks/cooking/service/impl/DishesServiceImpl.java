package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quarks.cooking.mapper.DishesDao;
import com.quarks.cooking.pojo.bean.Dishes;
import com.quarks.cooking.service.DishesService;
import org.springframework.stereotype.Service;

/**
 * Dishes
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:59
 **/
@Service
public class DishesServiceImpl extends ServiceImpl<DishesDao, Dishes> implements DishesService {

}
