package com.quarks.cooking.pojo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Collect
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 21:02
 **/
@Data
public class CollectBuildReq{
    @NotBlank
    private String type;
    @Min(0)
    private Long data;
}
