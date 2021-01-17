package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;
import lombok.Data;

import java.util.List;

/**
 * List Of Moment
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-14 23:05
 **/
@Data
public class MomentsCellRsp implements HttpResponse {
    private Long mid;

    private ProfileRsp profile;

    private String content;

    private List<String> images;

    private Long createTime;

    private List<Comment> listOfComment;

    @Data
    public static class Comment{
        private Long cid;

        private String type;

        private ProfileRsp profile;

        private Long createTime;

        private String content;

        private String image;
    }
}
