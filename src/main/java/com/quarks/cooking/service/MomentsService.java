package com.quarks.cooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quarks.cooking.pojo.bean.Moment;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.rsp.MomentsCellRsp;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:56
 **/
public interface MomentsService extends IService<Moment> {

    PageOfInfoListRsp<MomentsCellRsp> fetchMomentDetailWithPageInfo(Integer pageNo, Integer pageSize);

    MomentsCellRsp fetchMomentDetailByMomentId(Long mid);

    PageOfInfoListRsp<MomentsCellRsp> fetchMomentDetailListByMomentPosterUid(Integer uid, Integer pageNo, Integer pageSize);
}
