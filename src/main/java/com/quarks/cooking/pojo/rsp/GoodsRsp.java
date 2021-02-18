package com.quarks.cooking.pojo.rsp;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.quarks.cooking.pojo.common.HttpResponse;
import com.quarks.cooking.utils.JsonConvertUtil;
import lombok.Data;

import java.util.List;

/**
 * Goods
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-18 13:48
 **/
@Data
public class GoodsRsp implements HttpResponse {
    /**
     * gid
     */
    @JsonProperty("id")
    private Long gid;

    /**
     * 商品类型 参加还是食品
     */
    private String type;

    /**
     * 创建人
     */
    private String name;

    /**
     * 商品描述
     */
    private String brief;

    /**
     * 海报
     */
    private String poster;

    /**
     * 描述图片
     */
    private String images;

    /**
     * 价格
     */
    private Double price;

    /**
     * 状态
     */
    private Integer status;

    public List<String> getImages() {
        return JsonConvertUtil.valueOfList(images,String.class);
    }
}
