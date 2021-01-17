package com.quarks.cooking.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户发表的动态
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-13 14:17
 **/
@Data
@TableName("moments")
public class Moment {
    @TableId(value = "mid",type = IdType.AUTO)
    private Long mid;

    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "content")
    private String content;

    @TableField(value = "images")
    private String images;

    @TableField(value = "create_time")
    private Long createTime;

    @TableField(value = "update_time")
    private Long updateTime;

    @TableField(value = "status")
    private Integer status;
}
