package com.quarks.cooking.pojo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 评论用户Moment请求
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-14 21:42
 **/

@Data
public class CommentMomentReq {
    private String content;

    private String image;
    @NotBlank
    private String type;
    @Min(1)
    private Long mid;
}
