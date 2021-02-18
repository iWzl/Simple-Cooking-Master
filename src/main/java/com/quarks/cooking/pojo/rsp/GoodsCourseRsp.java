package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

import java.util.List;


/**
 * Curriculum
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-24 14:36
 **/
@Data
public class GoodsCourseRsp implements HttpResponse {
    private List<Course> courseList;

    @Data
    public static class Course{
        private Integer cid;
        private String name;
        private String image;
        private String description;
        private ProfileRsp chefProfile;
        private String type;
        private Double price;
        private Long refreshTime;
    }

}
