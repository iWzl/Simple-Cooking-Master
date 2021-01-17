package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quarks.cooking.mapper.AccountDao;
import com.quarks.cooking.mapper.CommentsDao;
import com.quarks.cooking.mapper.MomentsDao;
import com.quarks.cooking.pojo.bean.Account;
import com.quarks.cooking.pojo.bean.Comment;
import com.quarks.cooking.pojo.bean.Moment;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.rsp.MomentsCellRsp;
import com.quarks.cooking.pojo.rsp.ProfileRsp;
import com.quarks.cooking.service.MomentsService;
import com.quarks.cooking.utils.JsonConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Moments
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:59
 **/
@Service
public class MomentsServiceImpl extends ServiceImpl<MomentsDao, Moment> implements MomentsService {
    @Resource
    private MomentsDao momentsDao;
    @Resource
    private AccountDao accountDao;
    @Resource
    private CommentsDao commentsDao;

    @Override
    public MomentsCellRsp fetchMomentDetailByMomentId(Long mid) {
        Map<Integer, ProfileRsp> profileRspMap = new HashMap<>(32);
        MomentsCellRsp momentsCellRsp = new MomentsCellRsp();
        Moment moment = momentsDao.selectById(mid);
        if(null == moment){
            return momentsCellRsp;
        }
        return getMomentsCellRsp(profileRspMap, momentsCellRsp, moment);
    }

    private MomentsCellRsp getMomentsCellRsp(Map<Integer, ProfileRsp> profileRspMap, MomentsCellRsp momentsCellRsp, Moment moment) {
        BeanUtils.copyProperties(moment,momentsCellRsp,"images");
        momentsCellRsp.setImages(JsonConvertUtil.valueOfList(moment.getImages(),String.class));
        momentsCellRsp.setProfile(fetchProfileRsp(profileRspMap,moment.getUid()));
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mid",moment.getMid());
        List<Comment> commentList = commentsDao.selectList(queryWrapper);
        List<MomentsCellRsp.Comment> commentListRsp = new ArrayList<>();
        for (Comment comment : commentList) {
            MomentsCellRsp.Comment commentCell = new MomentsCellRsp.Comment();
            BeanUtils.copyProperties(comment,commentCell);
            commentCell.setProfile(fetchProfileRsp(profileRspMap,comment.getUid()));
            commentListRsp.add(commentCell);
        }
        momentsCellRsp.setListOfComment(commentListRsp);
        return momentsCellRsp;
    }

    @Override
    public PageOfInfoListRsp<MomentsCellRsp> fetchMomentDetailWithPageInfo(Integer pageNo, Integer pageSize) {
        Map<Integer, ProfileRsp> profileRspMap = new HashMap<>(32);
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Moment> momentPageInfo = new PageInfo<>(momentsDao.selectAllMomentsOrderByCreateTime());
        return PageOfInfoListRsp.build(momentPageInfo,moment->{
            MomentsCellRsp momentsCellRsp = new MomentsCellRsp();
            return getMomentsCellRsp(profileRspMap, momentsCellRsp, moment);
        });
    }

    @Override
    public PageOfInfoListRsp<MomentsCellRsp> fetchMomentDetailListByMomentPosterUid(Integer uid, Integer pageNo, Integer pageSize) {
        Map<Integer, ProfileRsp> profileRspMap = new HashMap<>(32);
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Moment> momentPageInfo = new PageInfo<>(momentsDao.selectAllMomentsOrderByCreateTimeWithUid(uid));
        return PageOfInfoListRsp.build(momentPageInfo,moment->{
            MomentsCellRsp momentsCellRsp = new MomentsCellRsp();
            return getMomentsCellRsp(profileRspMap, momentsCellRsp, moment);
        });
    }

    private ProfileRsp fetchProfileRsp(Map<Integer, ProfileRsp> profileRspMap, Integer uid){
        if(profileRspMap.containsKey(uid)){
            return profileRspMap.get(uid);
        }else {
            ProfileRsp profileRsp = new ProfileRsp();
            Account account = accountDao.selectById(uid);
            BeanUtils.copyProperties(account,profileRsp);
            profileRspMap.put(uid,profileRsp);
            return profileRsp;
        }
    }
}
