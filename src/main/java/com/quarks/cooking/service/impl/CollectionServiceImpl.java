package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quarks.cooking.mapper.CollectionDao;
import com.quarks.cooking.pojo.bean.Collection;
import com.quarks.cooking.service.CollectionService;
import org.springframework.stereotype.Service;

/**
 * Collection Service
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 20:56
 **/
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionDao, Collection> implements CollectionService {

}
