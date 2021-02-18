package com.quarks.cooking.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("goods")
public class Goods {
    /**
     * gid
     */
    @TableId(value = "gid",type = IdType.AUTO)
    private Long gid;

    /**
     * 商品类型 参加还是食品
     */
    @TableField(value = "type")
    private String type;

    /**
     * 创建人
     */
    @TableField(value = "name")
    private String name;

    /**
     * 商品描述
     */
    @TableField(value = "brief")
    private String brief;

    /**
     * 海报
     */
    @TableField(value = "poster")
    private String poster;

    /**
     * 描述图片
     */
    @TableField(value = "images")
    private String images;

    /**
     * 价格
     */
    @TableField(value = "price")
    private Double price;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;
}

