package com.quarks.cooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.quarks.cooking.pojo.bean.Moment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:52
 **/

@Repository
public interface MomentsDao extends BaseMapper<Moment> {
    List<Moment> selectAllMomentsOrderByCreateTime();

    List<Moment> selectAllMomentsOrderByCreateTimeWithUid(@Param("uid") Integer uid);
}
