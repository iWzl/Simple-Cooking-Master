package com.quarks.cooking.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Dishes")
public class Dishes {
    @TableId(value = "did",type = IdType.AUTO)
    private Integer did;

    @TableField(value = "group")
    private String group;

    @TableField(value = "type")
    private String type;

    @TableField(value = "time")
    private String time;

    @TableField(value = "name")
    private String name;

    @TableField(value = "image")
    private String image;

    @TableField(value = "description")
    private String description;

    @TableField(value = "material")
    private String material;

    @TableField(value = "chef_id")
    private Integer chefId;

    @TableField(value = "top")
    private Integer top;

    @TableField(value = "course_id")
    private Integer courseId;

    @TableField(value = "refresh_time")
    private Long refreshTime;
}

