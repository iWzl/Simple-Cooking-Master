package com.quarks.cooking.pojo.rsp;

import lombok.Data;

@Data
public class DishesRsp {
    private Integer did;

    private String group;

    private String type;

    private String time;

    private String name;

    private String image;

    private String description;

    private String material;

    private ProfileRsp chefProfile;

    private Integer top;

    private Integer courseId;

    private Long refreshTime;
}

