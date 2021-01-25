package com.quarks.cooking.pojo.rsp;

import lombok.Data;


/**
 * DishesRsp
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-13 13:43
 **/

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

