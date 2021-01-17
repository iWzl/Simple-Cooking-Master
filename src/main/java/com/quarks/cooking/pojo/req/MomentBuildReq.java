package com.quarks.cooking.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * Moment创建
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-13 14:54
 **/
@Data
public class MomentBuildReq {

    private String content;

    private List<String> images;

}
