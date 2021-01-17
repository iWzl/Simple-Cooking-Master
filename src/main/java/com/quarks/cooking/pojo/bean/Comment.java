package com.quarks.cooking.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Comments
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-13 14:36
 **/
@Data
@TableName("comments")
public class Comment {
    @TableId(value = "cid",type = IdType.AUTO)
    private Long cid;

    @TableField(value = "mid")
    private Long mid;

    @TableField(value = "type")
    private String type;

    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "create_time")
    private Long createTime;

    @TableField(value = "content")
    private String content;

    @TableField(value = "image")
    private String image;

    @TableField(value = "status")
    private Integer status;
}
