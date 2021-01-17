package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quarks.cooking.mapper.CommentsDao;
import com.quarks.cooking.pojo.bean.Comment;
import com.quarks.cooking.service.CommentsService;
import org.springframework.stereotype.Service;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:59
 **/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsDao, Comment> implements CommentsService {
}
