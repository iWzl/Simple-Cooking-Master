package com.quarks.cooking.controller;

import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.ResultCodeEnum;
import com.quarks.cooking.pojo.rsp.UploadImageRsp;
import com.quarks.cooking.service.CommonService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 工具类
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 19:54
 **/

@Controller
@RequestMapping("/tools")
public class ToolsController {
    final
    CommonService commonService;

    public ToolsController(CommonService commonService) {
        this.commonService = commonService;
    }

    @ResponseBody
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Security
    public Msg<UploadImageRsp> upload(@NotNull @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            Msg.buildFailedMsg(ResultCodeEnum.IMAGE_IS_EMPTY, "picture does not exist");
        }
        String imageFilePath =  commonService.uploadMultipartFile(imageFile);
        if(!"".equals(imageFilePath)){
            UploadImageRsp uploadImageRsp = new UploadImageRsp();
            uploadImageRsp.setImagePath(imageFilePath);
            return Msg.buildSuccessMsg("file upload successfully", uploadImageRsp);
        }else {
            return Msg.buildFailedMsg("file save failed");
        }
    }
}
