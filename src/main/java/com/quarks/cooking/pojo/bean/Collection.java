package com.quarks.cooking.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Collection
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 20:51
 **/
@Data
@TableName("collection")
public class Collection {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("uid")
    private Integer uid;

    @TableField("type")
    private String type;

    @TableField("data")
    private Long data;

    @TableField("status")
    private Integer status;



}
