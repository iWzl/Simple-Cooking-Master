package com.quarks.cooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quarks.cooking.pojo.bean.Banner;
import com.quarks.cooking.pojo.bean.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Goods
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:52
 **/

@Repository
public interface GoodsDao extends BaseMapper<Goods> {
    List<Goods> fetchGoodsByType(@Param("type") String type);

    List<Banner> fetchAllBanner();
}
