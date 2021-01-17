package com.quarks.cooking.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:44
 **/
@Data
@TableName("account")
public class Account {
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;

    @TableField("account")
    private String account;

    @TableField("password")
    private String password;

    @TableField("name")
    private String name;

    @TableField("gender")
    private String gender;

    @TableField("about_me")
    private String aboutMe;

    @TableField("avatar")
    private String avatar;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private Long createTime;

    @TableField("update_time")
    private Long updateTime;

    @TableField("last_login_time")
    private Long lastLoginTime;
}
